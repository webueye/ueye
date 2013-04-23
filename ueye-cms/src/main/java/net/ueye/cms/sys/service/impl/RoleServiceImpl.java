package net.ueye.cms.sys.service.impl;

import java.util.Collection;
import java.util.List;

import net.ueye.cms.sys.entity.Role;
import net.ueye.cms.sys.service.RoleService;
import net.ueye.commons.orm.dao.HibernateDaoSupport;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-19
 */
@Service
public class RoleServiceImpl extends HibernateDaoSupport<Role> implements RoleService {
	
	@SuppressWarnings("unchecked")
	public List<Role> findRoles(Collection<Long> roleIds) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.in("id", roleIds));
		return detachedCriteria.getExecutableCriteria(getSession()).list();
	}

}
