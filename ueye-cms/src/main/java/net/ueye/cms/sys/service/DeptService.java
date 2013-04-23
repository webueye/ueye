package net.ueye.cms.sys.service;

import java.io.Serializable;
import java.util.List;

import net.ueye.cms.sys.entity.Dept;
import net.ueye.commons.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-21
 */
public interface DeptService extends BaseDao<Dept> {

	List<Dept> findDepts(Dept dept, boolean parentIsNull);

	List<Dept> findDeptsByParent(Serializable parentId);

}
