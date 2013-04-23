package net.ueye.cms.sys.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.ueye.cms.Module;
import net.ueye.cms.commons.controller.CommonController;
import net.ueye.cms.sys.controller.path.ResultPath;
import net.ueye.cms.sys.entity.Menu;
import net.ueye.cms.sys.entity.Role;
import net.ueye.cms.sys.service.MenuService;
import net.ueye.cms.sys.service.RoleService;
import net.ueye.commons.bean.Page;
import net.ueye.commons.controller.ViewName;
import net.ueye.commons.orm.PropertyFilter;
import net.ueye.commons.util.StringUtils;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-19
 */
@Controller
@RequestMapping(ResultPath.role)
public class RoleController extends CommonController {

	@RequestMapping
	public String list(Page page, Model model) {
		roleService.findDatas("company.id", getAccount(model).getId(), page);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page) {
		logger.debug("search: page[{}]", page);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		roleService.findPage(page, filters);
		return forward(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew() {
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Role role) {
		logger.debug("create: role[{}]", role);

		roleService.save(role);
		return redirect(ResultPath.role);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id) {
		logger.debug("edit: id[{}]", id);
		return forward(ViewName.edit);
	}

	@RequestMapping(value = "/update/{role.id}", method = RequestMethod.POST)
	public String update(Role role) {
		logger.debug("update: role[{}]", role);

		roleService.saveOrUpdate(role);
		return redirect(ResultPath.role);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("remove: id[{}]", id);

		roleService.delete(id);
		return redirect(ResultPath.role);
	}

	@ModelAttribute("role")
	public Role getRole(HttpServletRequest request) {
		return (Role) prepare(request, Role.class);
	}

	@Override
	protected Object get(Long id) {
		return roleService.get(id);
	}

	@RequestMapping(value = "/menu/{id}")
	public String menu(@PathVariable Long id, Model model) {

		Role role = roleService.get(id);
		Multimap<Long, Menu> group = HashMultimap.create();
		List<Menu> menus = null;

//		if (BooleanUtils.isTrue(getAccount(model).getRoleType())) {
//			menus = menuService.findAll();
//		} else {
//			menus = menuService.findDatas("roleType", Boolean.FALSE);
//		}

//		for (Menu menu : menus) {
//			if (menu.getParent() != null) {
//				group.put(menu.getParent().getId(), menu);
//			}
//		}
		List<Menu> rootMenus = Lists.newArrayList();
//		for (Menu menu : menus) {
//			menu.setExpanded(Boolean.TRUE);
//			List<Menu> child = Lists.newArrayList();
//			child.addAll(group.get(menu.getId()));
//			menu.setChild(sort(child));
//			if (menu.getParent() == null) {
//				rootMenus.add(menu);
//			}
//			if (role.getMenus() != null) {
//				for (Long menuId : role.getMenus()) {
//					if (menu.getId().longValue() == menuId.longValue()) {
//						menu.setChecked(NodeModel.CHECKED);
//					}
//				}
//			}
//		}
		model.addAttribute("roleMenus", rootMenus).addAttribute("role", role);
		return "/sys/role/role-menu-authorize";
	}

	@RequestMapping(value = "/authorize", method = RequestMethod.POST)
	public String authorize(Long id, String[] menus) {
		Role role = roleService.get(id);
		role.setMenus(StringUtils.stringToSet(menus));
		roleService.update(role);
		return redirect(ResultPath.role);
	}

	private List<Menu> sort(List<Menu> menus) {
		Collections.sort(menus, new Comparator<Menu>() {

			@Override
			public int compare(Menu m1, Menu m2) {
				return m1.getOrderValue() - m2.getOrderValue();
			}
		});
		return menus;
	}

	@Override
	protected String getModule() {
		return Module.sys;
	}

	private RoleService roleService;
	private MenuService menuService;

	@Required
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Required
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
