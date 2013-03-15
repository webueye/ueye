package net.ueye.cms.commons.service;

import java.io.Serializable;
import java.util.List;

import net.ueye.commons.bean.Page;
import net.ueye.commons.orm.entity.BaseEntity;

/**
 * @author rubys
 * @since 2013-3-15
 */
public interface BaseService<T extends BaseEntity> {

	T get(Serializable id);

	void save(T t);

	void update(T t);

	void saveOrUpdate(T t);

	void delete(Serializable id);

	List<T> findData(Page page);

}
