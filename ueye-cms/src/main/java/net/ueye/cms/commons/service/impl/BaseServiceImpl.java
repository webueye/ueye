package net.ueye.cms.commons.service.impl;

import java.io.Serializable;
import java.util.List;

import net.ueye.cms.commons.service.BaseService;
import net.ueye.commons.bean.Page;
import net.ueye.commons.orm.dao.BaseDao;
import net.ueye.commons.orm.entity.BaseEntity;

/**
 * @author rubys
 * @since 2013-3-15
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
	
	public abstract BaseDao<T> getBaseDao();

	@Override
	public T get(Serializable id) {
		return getBaseDao().get(id);
	}

	@Override
	public void save(T t) {
		getBaseDao().save(t);
	}

	@Override
	public void update(T t) {
		getBaseDao().update(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		getBaseDao().saveOrUpdate(t);
	}

	@Override
	public void delete(Serializable id) {
		getBaseDao().delete(id);
	}

	@Override
	public List<T> findData(Page page) {
		return getBaseDao().findPage(page);
	}


}
