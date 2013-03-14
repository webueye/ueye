package net.ueye.commons.orm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import net.ueye.commons.orm.entity.BaseEntity;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@Transactional(readOnly = true)
public abstract class BaseDaoSupport<T extends BaseEntity> implements BaseDao<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) getSession().get(getEntityClass(), id);
	}

	@Override
	@Transactional
	public void insert(Object entity) {
		getSession().save(entity);
	}

	@Override
	@Transactional
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	@Transactional
	public void merge(T entity) {
		getSession().merge(entity);
	}

	@Override
	@Transactional
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	@Transactional
	public void delete(Serializable id) {
		T entity = get(id);
		if (entity == null) {
			return;
		}
		getSession().delete(entity);
	}

	@Override
	public void evict(Object... entities) {
		if (entities == null) {
			return;
		}
		for (Object entity : entities) {
			getSession().evict(entity);
		}
	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return createDetachedCriteria().getExecutableCriteria(getSession()).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findDatas(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findDatas(String propertyName, Object value) {
		Criteria criteria = createDetachedCriteria().getExecutableCriteria(getSession());
		if (propertyName.indexOf(".") != -1) {
			String alias = propertyName.substring(0, propertyName.indexOf("."));
			criteria.createAlias(alias, alias);
		}
		criteria.add(Restrictions.eq(propertyName, value));
		return criteria.list();
	}

	@Override
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}

	@Override
	public DetachedCriteria createDetachedCriteria(String alias) {
		return DetachedCriteria.forClass(getEntityClass(), alias);
	}

	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		try {
			return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			return (Class<T>) getEntity(getClass().getSuperclass().getGenericInterfaces());
		}
	}

	protected Class<?> getEntity(Type[] types) {
		Class<?> entityClass = null;
		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				entityClass = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
			} else if (type instanceof Class) {
				entityClass = getEntity(((Class<?>) type).getGenericInterfaces());
			}
		}
		logger.debug("ActualTypeArguments [entityClass {}]", entityClass);
		return entityClass;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
