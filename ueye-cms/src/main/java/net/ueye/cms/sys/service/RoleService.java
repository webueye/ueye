package net.ueye.cms.sys.service;

import java.util.Collection;
import java.util.List;

import net.ueye.cms.sys.entity.Role;
import net.ueye.commons.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-19
 */
public interface RoleService extends BaseDao<Role> {

	List<Role> findRoles(Collection<Long> roleIds);

}
