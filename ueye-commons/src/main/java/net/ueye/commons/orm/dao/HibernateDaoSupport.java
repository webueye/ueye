package net.ueye.commons.orm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.ueye.commons.bean.Page;
import net.ueye.commons.orm.PropertyFilter;
import net.ueye.commons.orm.PropertyFilter.MatchType;
import net.ueye.commons.orm.entity.BaseEntity;
import net.ueye.commons.util.ReflectionUtils;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author rubys
 * @since 2012-5-31
 */
public class HibernateDaoSupport<T extends BaseEntity> extends BaseDaoSupport<T> {

	@Override
	public List<T> findPage(Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		return findPage(detachedCriteria, page);
	}

	@Override
	public List<T> findPage(Object object, Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Example.create(object));
		return findPage(detachedCriteria, page);
	}

	@Override
	public List<T> findDatas(String propertyName, Object value, Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq(propertyName, value));
		return findPage(detachedCriteria, page);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findPage(DetachedCriteria detachedCriteria, Page page) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		if (page.isAutoCount()) {
			page.setTotalCount(countCriteriaResult(criteria));
		}

		setPageParameterToCriteria(criteria, page);
		page.setDatas(criteria.list());
		return (List<T>) page.getDatas();
	}

	protected Criteria setPageParameterToCriteria(final Criteria criteria, final Page page) {
		Assert.isTrue(page.getPageSize() > 0, "Page Size must larger than zero");

		criteria.setFirstResult(page.getBeginIndex());
		criteria.setMaxResults(page.getPageSize());
		if (page.isOrderBySetted()) {
			if (Page.ASC.equals(page.getOrder())) {
				criteria.addOrder(Order.asc(page.getOrderBy()));
			} else {
				criteria.addOrder(Order.desc(page.getOrderBy()));
			}
		}
		return criteria;
	}

	@Override
	public Long count() {
		String entityName = getEntityClass().getSimpleName();
		return (Long) getSession().createQuery("SELECT count(*) FROM " + entityName).uniqueResult();
	}

	@Override
	public Long count(String propertyName, Object value) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq(propertyName, value));
		return countCriteriaResult(detachedCriteria.getExecutableCriteria(getSession()));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long countCriteriaResult(final Criteria criteria) {
		CriteriaImpl impl = (CriteriaImpl) criteria;

		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries = null;

		try {
			orderEntries = (List<OrderEntry>) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("countCriteriaResult[{}]", e.getMessage());
		}

		Long totalCountObject = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = (totalCountObject != null) ? totalCountObject : 0;

		criteria.setProjection(projection);

		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			criteria.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("countCriteriaResult setFieldValue [{}]", e.getMessage());
		}
		return totalCount;
	}

	protected Criteria createCriteria(final Criterion... criterions) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		for (Criterion c : criterions) {
			detachedCriteria.add(c);
		}
		return detachedCriteria.getExecutableCriteria(getSession());
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findPage(final Page page, final List<PropertyFilter> filters) {
		Assert.notNull(page, "page is required.");
		Criteria criteria = createCriteria(buildCriterionByPropertyFilter(filters));

		createAlias(criteria, filters);

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(criteria);
			page.setTotalCount(totalCount);
		}

		setPageParameterToCriteria(criteria, page);
		page.setDatas(criteria.list());
		return (List<T>) page.getDatas();
	}

	public List<Map<String, Object>> findPageByNative(final String sql, final Page page) {
		return getSession().doReturningWork(new ReturningWork<List<Map<String, Object>>>() {

			@Override
			public List<Map<String, Object>> execute(Connection connection) throws SQLException {
				List<Map<String, Object>> list = Lists.newArrayList();
				try {
					page.setTotalCount(count(sql, connection));
					StringBuilder builder = new StringBuilder();
					builder.append(sql).append(" limit ").append(page.getBeginIndex()).append(", ").append(page.getEndIndex());
					Statement stmt = connection.createStatement();
					ResultSet rs = stmt.executeQuery(builder.toString());
					ResultSetMetaData md = rs.getMetaData();
					int columnCount = md.getColumnCount();
					while (rs.next()) {
						Map<String, Object> map = Maps.newHashMap();
						for (int i = 1; i <= columnCount; i++) {
							map.put(md.getColumnName(i), rs.getObject(i));
						}
						list.add(map);
					}
					page.setDatas(list);
					return list;
				} catch (SQLException e) {
					logger.error("Query[{}] error", sql, e);
					throw new IllegalStateException(e);
				}
			}

			private long count(String sql, Connection connection) throws SQLException {
				StringBuilder builder = new StringBuilder();
				builder.append("select count(*) c from (").append(sql).append(") alias");
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(builder.toString());
				if (rs.next())
					return rs.getLong("c");
				return 0L;
			}

		});
	}

	protected void createAlias(Criteria criteria, final List<PropertyFilter> filters) {
		for (PropertyFilter filter : filters) {
			for (String propertyName : filter.getPropertyNames()) {
				if (propertyName.indexOf(".") != -1) {
					String aliasPrefix = propertyName.substring(0, propertyName.lastIndexOf("."));
					String[] aliases = aliasPrefix.split("\\.");

					StringBuilder sb = new StringBuilder();
					for (String alias : aliases) {
						if (sb.length() > 0) {
							sb.append(".");
						}
						sb.append(alias);
						criteria.createAlias(sb.toString(), sb.toString().replaceAll("\\.", "_"));
					}
				}
			}
		}
	}

	protected Criterion[] buildCriterionByPropertyFilter(final List<PropertyFilter> filters) {
		List<Criterion> criterions = Lists.newArrayList();
		for (PropertyFilter filter : filters) {
			if (filter.hasMultiProperties()) {
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildCriterion(param, filter.getMatchValue(), filter.getMatchType());
					disjunction.add(criterion);
				}
				criterions.add(disjunction);
			} else {
				Criterion criterion = buildCriterion(filter.getPropertyName(), filter.getMatchValue(), filter.getMatchType());
				criterions.add(criterion);
			}
		}
		return criterions.toArray(new Criterion[criterions.size()]);
	}

	protected Criterion buildCriterion(String propertyName, final Object propertyValue, final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName is required.");

		String[] dots = propertyName.split("\\.");
		if (dots.length > 2) {
			String alias = propertyName.substring(0, propertyName.lastIndexOf("."));
			String last = propertyName.substring(propertyName.lastIndexOf("."), propertyName.length());
			propertyName = alias.replaceAll("\\.", "_") + last;
		}

		Criterion criterion = null;
		switch (matchType) {
		case EQ:
			criterion = Restrictions.eq(propertyName, propertyValue);
			break;
		case LIKE:
			criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			break;
		case LE:
			criterion = Restrictions.le(propertyName, propertyValue);
			break;
		case LT:
			criterion = Restrictions.lt(propertyName, propertyValue);
			break;
		case GE:
			criterion = Restrictions.ge(propertyName, propertyValue);
			break;
		case GT:
			criterion = Restrictions.gt(propertyName, propertyValue);
			break;
		case BA:
			if (propertyValue instanceof List) {
				List<?> values = (List<?>) propertyValue;
				if (values.size() == 2) {
					criterion = Restrictions.between(propertyName, values.get(0), values.get(1));
				}
			}
		}
		return criterion;
	}

}
