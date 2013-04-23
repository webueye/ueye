package net.ueye.cms.sys.service;

import java.io.Serializable;
import java.util.List;

import net.ueye.cms.sys.entity.Menu;
import net.ueye.commons.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
public interface MenuService extends BaseDao<Menu> {

	List<Menu> getRootMenus();

	List<Menu> findMenus(Menu menu, boolean parentIsNull);

	List<Menu> findMenusByParent(Serializable parentId);

	List<Menu> findMenusByParent(Serializable parentId, boolean isLeaf);

	List<Menu> loopQueryMenusByParent(List<Menu> menus);

	List<Menu> loopQueryMenusByParent(List<Menu> menus, boolean isLeaf);

	List<Menu> handle(long menuId, List<Menu> menus);

	List<Menu> updateMenus(List<Menu> menus, Menu menu, long id, String type);

}
