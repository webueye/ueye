package net.ueye.cms.sys.service.impl;

import java.io.Serializable;
import java.util.List;

import net.ueye.cms.sys.entity.Dept;
import net.ueye.cms.sys.service.DeptService;
import net.ueye.commons.orm.dao.HibernateDaoSupport;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-21
 */
@Service
public class DeptServiceImpl extends HibernateDaoSupport<Dept> implements
		DeptService {

	@Override
	public List<Dept> findDepts(Dept dept, boolean parentIsNull) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Example.create(dept));
		if (parentIsNull) {
			detachedCriteria.add(Restrictions.isNull("parent"));
		}
		return findDatas(detachedCriteria);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Dept> findDeptsByParent(Serializable parentId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq("parent.id", parentId));
		return criteria.list();
	}

}
