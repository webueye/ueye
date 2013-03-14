package net.ueye.commons.orm.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import net.ueye.commons.bean.Page;
import net.ueye.commons.orm.PropertyFilter;
import net.ueye.commons.orm.entity.BaseEntity;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @author rubys
 * @since 2012-5-29
 */
public interface BaseDao<T extends BaseEntity> {

	T get(Serializable id);

	void insert(Object entity);

	void save(T entity);

	void merge(T entity);

	void update(T entity);

	void saveOrUpdate(T entity);

	void delete(T entity);

	void delete(Serializable id);

	void evict(Object... entities);

	void clear();

	List<T> findAll();

	List<T> findDatas(String propertyName, Object value);

	List<T> findPage(Page page);

	List<T> findPage(Object object, Page page);

	List<T> findDatas(String propertyName, Object value, Page page);

	List<T> findPage(DetachedCriteria detachedCriteria, Page page);

	List<T> findPage(final Page page, final List<PropertyFilter> filters);

	List<Map<String, Object>> findPageByNative(final String sql, final Page page);

	Long count();

	Long count(String propertyName, Object value);

	long countCriteriaResult(final Criteria criteria);

	DetachedCriteria createDetachedCriteria();

	DetachedCriteria createDetachedCriteria(String alias);

}