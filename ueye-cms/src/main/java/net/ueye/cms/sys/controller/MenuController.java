package net.ueye.cms.sys.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.ueye.cms.Const;
import net.ueye.cms.Module;
import net.ueye.cms.commons.controller.CommonController;
import net.ueye.cms.sys.controller.path.ResultPath;
import net.ueye.cms.sys.entity.Menu;
import net.ueye.cms.sys.service.MenuService;
import net.ueye.commons.controller.ViewName;
import net.ueye.commons.util.BeanUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends CommonController {

	@RequestMapping
	public String list(HttpSession session) {
		List<Menu> menus = menuService.getRootMenus();
		menuService.loopQueryMenusByParent(menus);
		session.setAttribute("menus", menuService.loopQueryMenusByParent(menus));
		return forward(ViewName.list);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/left")
	public String left(HttpSession session, Model model) {
		Multimap<Long, Menu> menuGroup = (Multimap<Long, Menu>) session.getServletContext().getAttribute("menuGroup");
		Multimap<Long, Menu> duplicates = duplicate(menuGroup.values());

//		Account account = getAccount(model);
//		if (BooleanUtils.isTrue(account.getCompany().getRoleType()) && BooleanUtils.isTrue(account.getAdmin())) {
//
//		} else {
//			Set<Long> menuIds = Sets.newHashSet();
//			if (BooleanUtils.isNotTrue(account.getCompany().getRoleType())) {
//				for (Menu menu : duplicates.values()) {
//					if (BooleanUtils.isNotTrue(menu.getRoleType())) {
//						menuIds.add(menu.getId());
//					}
//				}
//			}
//
//			if (BooleanUtils.isNotTrue(account.getAdmin())) {
//				if (CollectionUtils.isNotEmpty(account.getRoles())) {
//					List<Role> roles = roleService.findRoles(account.getRoles());
//					for (Role role : roles) {
//						menuIds.addAll(role.getMenus());
//					}
//				}
//			}
//			List<Menu> menus = Lists.newArrayList();
//			for (Menu menu : duplicates.values()) {
//				if (menuIds.contains(menu.getId())) {
//					menus.add(menu);
//				}
//			}
//			duplicates = duplicate(menus);
//		}
		session.setAttribute("menus", sort(duplicates.get(-1L)));
		return redirect("/main/left.jsp");
	}

	@RequestMapping("/edit-new")
	public String editNew() {
		return forward(ViewName.insert);
	}

	@RequestMapping("/new/{parentId}")
	public String editNew(Menu menu, @PathVariable String parentId, Model model) {
		logger.debug("menu[{}], parentId[{}]", menu, parentId);
		if (StringUtils.isNotBlank(parentId)) {
			Menu parent = new Menu();
			parent.setId(Long.parseLong(parentId));
			menu.setParent(parent);
			model.addAttribute("menu", menu);
		}
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpSession session, Menu menu, String parentId) {
		logger.debug("create[{}]", menu);

		if (StringUtils.isNotBlank(parentId)) {
			Menu parent = menuService.get(Long.parseLong(parentId));
			menu.setWidth(parent.getWidth() + 1);
			menu.setParent(parent);
		}
		menuService.save(menu);
		refresh(session);
		return redirect(ResultPath.menu);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(HttpSession session, Menu menu, String parentId) {
		logger.debug("create[{}]", menu);

		if (StringUtils.isNotBlank(parentId)) {
			Menu parent = menuService.get(Long.parseLong(parentId));
			menu.setWidth(parent.getWidth() + 1);
			menu.setParent(parent);
		}
		menuService.merge(menu);
		refresh(session);
		return redirect(ResultPath.menu);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("{id}/expand")
	public String expand(@PathVariable long id, HttpSession session) {
		List<Menu> menus = (List<Menu>) session.getAttribute("menus");
		menuService.handle(id, menus);
		session.setAttribute("menus", menus);
		return forward(ViewName.list);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("{id}/menu")
	public String menu(@PathVariable long id, HttpSession session) {
		List<Menu> menus = (List<Menu>) session.getAttribute("menus");
		menuService.handle(id, menus);
		session.setAttribute("menus", menus);
		return redirect("/main/left.jsp");
	}

	@RequestMapping("{id}")
	public String show(@PathVariable long id, Model model) {
		logger.debug("show[{}]", id);

		model.addAttribute("menu", menuService.get(id));
		return forward(ViewName.edit);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/destroy/{id}")
	public String remove(@PathVariable long id, HttpSession session) {
		logger.debug("remove[{}]", id);
		menuService.delete(id);
		List<Menu> menus = (List<Menu>) session.getAttribute("menus");
		menuService.updateMenus(menus, new Menu(), id, Const.delete);
		
		refresh(session);
		return redirect(ResultPath.menu);
	}

	@RequestMapping(value = "/nodes", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String nodes() {
		List<Menu> list = new ArrayList<Menu>();
		Menu rootMenu = new Menu();
		rootMenu.setLeaf(false);

		List<Menu> rootMenus = menuService.findMenus(rootMenu, true);
		forEachMenus(list, rootMenus);
		return JSONArray.fromObject(list).toString();
	}

	@Override
	protected Object get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getModule() {
		return Module.sys;
	}

	@SuppressWarnings("unchecked")
	private void forEachMenus(List<Menu> list, List<Menu> menus) {
		if (CollectionUtils.isEmpty(menus)) {
			return;
		}

		for (Menu menu : menus) {
			if (menu.getLeaf()) {
				continue;
			}

			Menu m = new Menu();
			m.setId(menu.getId());
			m.setLabel(indent(menu.getWidth()) + menu.getLabel());
			m.setWidth(menu.getWidth());
			list.add(m);
			if (CollectionUtils.isNotEmpty(menu.getChild())) {
				forEachMenus(list, (List<Menu>) menu.getChild());
			} else {
				forEachMenus(list, menuService.findMenusByParent(m.getId(), false));
			}
		}
	}

	private String indent(Integer width) {
		if (width == null) {
			width = 0;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < width; i++) {
			sb.append("|--");
		}
		return sb.toString();
	}

	private Multimap<Long, Menu> duplicate(Collection<Menu> menus) {
		Multimap<Long, Menu> duplicates = HashMultimap.create();
		for (Menu menu : menus) {
			Menu duplicate = new Menu();
			BeanUtils.copyProperties(duplicate, menu);
			if (duplicate.getParent() == null) {
				duplicates.put(-1L, duplicate);
			} else {
				duplicates.put(duplicate.getParent().getId(), duplicate);
			}
		}
		for (Menu menu : duplicates.values()) {
			menu.setChild(sort(duplicates.get(menu.getId())));
		}
		return duplicates;
	}

	private void refresh(HttpSession session) {
		List<Menu> menus = menuService.findAll();

		Multimap<Long, Menu> menuGroup = HashMultimap.create();
		for (Menu menu : menus) {
			if (menu.getParent() == null) {
				menuGroup.put(-1L, menu);
			} else {
				menuGroup.put(menu.getParent().getId(), menu);
			}
		}
		session.getServletContext().setAttribute("menuGroup", menuGroup);
	}
	
	private List<Menu> sort(Collection<Menu> menus) {
		List<Menu> sortMenus = Lists.newArrayList(menus);
		Collections.sort(sortMenus, new Comparator<Menu>() {
			@Override
			public int compare(Menu m1, Menu m2) {
				int orderCompare = m1.getOrderValue() - m2.getOrderValue();
				if (orderCompare == 0) {
					return m1.getId().intValue() - m2.getId().intValue();
				}
				return orderCompare;
			}
		});
		return sortMenus;
	}

	private MenuService menuService;

	@Required
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
