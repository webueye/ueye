package net.ueye.cms.sys.service.impl;

import java.io.Serializable;
import java.util.List;

import net.ueye.cms.Const;
import net.ueye.cms.sys.entity.Menu;
import net.ueye.cms.sys.service.MenuService;
import net.ueye.commons.orm.dao.HibernateDaoSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@Transactional
@Service("menuService")
public class MenuServiceImpl extends HibernateDaoSupport<Menu> implements
		MenuService {

	@Override
	public List<Menu> getRootMenus() {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Restrictions.isNull("parent"));
		detachedCriteria.addOrder(Order.asc("orderValue"));
		return findDatas(detachedCriteria);
	}

	@Override
	public List<Menu> findMenus(Menu menu, boolean parentIsNull) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Example.create(menu));
		detachedCriteria.addOrder(Order.asc("orderValue"));
		if (parentIsNull) {
			detachedCriteria.add(Restrictions.isNull("parent"));
		}
		return findDatas(detachedCriteria);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> findMenusByParent(Serializable parentId) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq("parent.id", parentId));
		detachedCriteria.addOrder(Order.asc("orderValue"));
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> findMenusByParent(Serializable parentId, boolean isLeaf) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		criteria.add(Restrictions.eq("parent.id", parentId));
		criteria.add(Restrictions.eq("leaf", isLeaf));
		detachedCriteria.addOrder(Order.asc("orderValue"));
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> loopQueryMenusByParent(List<Menu> menus) {
		if (menus != null) {
			for (Menu menu : menus) {
				if (BooleanUtils.isTrue(menu.getExpanded())) {
					menu.setChild(findMenusByParent(menu.getId()));
					loopQueryMenusByParent((List<Menu>) menu.getChild());
				}
			}
		}
		return menus;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> loopQueryMenusByParent(List<Menu> menus, boolean isLeaf) {
		if (menus != null) {
			for (Menu menu : menus) {
				if (BooleanUtils.isTrue(menu.getExpanded())) {
					menu.setChild(findMenusByParent(menu.getId()));
					loopQueryMenusByParent((List<Menu>) menu.getChild(), isLeaf);
				}
			}
		}
		return menus;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> handle(long menuId, List<Menu> menus) {
		if (menus != null) {
			for (Menu menu : menus) {
				if (menu.getId() == menuId) {
					menu.setExpanded(!BooleanUtils.isTrue(menu.getExpanded()));

					if (BooleanUtils.isTrue(menu.getExpanded())
							&& CollectionUtils.isEmpty(menu.getChild())) {
						menu.setChild(findMenusByParent(menu.getId()));
						loopQueryMenusByParent((List<Menu>) menu.getChild());
					}
					return menus;
				} else {
					handle(menuId, (List<Menu>) menu.getChild());
				}
			}
		}
		return menus;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Menu> updateMenus(List<Menu> menus, Menu menu, long id,
			String type) {
		if (menus != null) {
			for (Menu chilMenu : menus) {
				if (id == chilMenu.getId()) {
					if (Const.delete.equals(type)) {
						menus.remove(chilMenu);
					} else if (Const.nonParentUpdate.equals(type)) {
						chilMenu.setName(menu.getName());
					} else if (Const.update.equals(type)) {
						((List<Menu>) chilMenu.getChild()).add(menu);
					} else if (Const.insert.equals(type)) {
						((List<Menu>) chilMenu.getChild()).add(menu);
					}
					return menus;
				}
				updateMenus((List<Menu>) chilMenu.getChild(), menu, id, type);
			}
		}
		return menus;
	}

}
