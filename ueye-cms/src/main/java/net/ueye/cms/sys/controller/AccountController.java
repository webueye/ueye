package net.ueye.cms.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.ueye.cms.Module;
import net.ueye.cms.commons.controller.CommonController;
import net.ueye.cms.sys.controller.path.ResultPath;
import net.ueye.cms.sys.entity.Account;
import net.ueye.cms.sys.entity.Dept;
import net.ueye.cms.sys.entity.Role;
import net.ueye.cms.sys.service.AccountService;
import net.ueye.cms.sys.service.RoleService;
import net.ueye.commons.bean.Page;
import net.ueye.commons.controller.ViewName;
import net.ueye.commons.orm.PropertyFilter;
import net.ueye.commons.util.EncodeUtils;
import net.ueye.commons.util.StringUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author rubys@vip.qq.com
 * @since 2013-4-23
 */
@Controller
@RequestMapping(ResultPath.account)
public class AccountController extends CommonController {

	@RequestMapping
	public String list(Page page, Model model) {
		accountService.findPage(page);
		return forward(ViewName.list);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Page page) {
		logger.debug("search: page[{}]", page);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		accountService.findPage(page, filters);
		return forward(ViewName.list);
	}

	@RequestMapping("/edit-new")
	public String editNew(Model model) {
		return forward(ViewName.insert);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(Account account, Model model, String deptId) {
		logger.debug("create: account[{}]", account);

		account.setPassword(EncodeUtils.md5(account.getPassword()));
		accountService.save(account);
		return redirect(ResultPath.account);
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable long id, Account account, Model model) {
		logger.debug("edit: id[{}], account[{}]", id, account);
		return forward(ViewName.edit);
	}

	@RequestMapping(value = "/update/{account.id}", method = RequestMethod.POST)
	public String update(Account account, String deptId, Model model) {
		logger.debug("update: account[{}]", account);

		if (org.apache.commons.lang.StringUtils.isNotBlank(deptId)) {
			account.setDept(new Dept(Long.valueOf(deptId)));
		}
		accountService.saveOrUpdate(account);
		return redirect(ResultPath.account);
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable long id) {
		logger.debug("show: id[{}]", id);
		return forward(ViewName.show);
	}

	@RequestMapping("/destroy/{id}")
	public String destroy(@PathVariable long id) {
		logger.debug("remove[{}]", id);

		accountService.delete(id);
		return redirect(ResultPath.account);
	}

	@RequestMapping("role/{id}")
	public String role(@PathVariable Long id, Model model) {
		Account account = accountService.get(id);
		Account currentAccount = getAccount(model);
		List<Role> roles = roleService.findDatas("account.id", currentAccount.getId());
		if (CollectionUtils.isNotEmpty(account.getRoles())) {
			for (Role role : roles) {
				for (Long roleId : account.getRoles()) {
					if (role.getId().longValue() == roleId.longValue()) {
						role.setChecked("checked");
					}
				}
			}
		}
		model.addAttribute("roles", roles);
		model.addAttribute("account", account);
		return forward("account-role-list");
	}

	@RequestMapping(value = "/authorize", method = RequestMethod.POST)
	public String authorize(Long id, String[] roleId) {
		Account account = accountService.get(id);
		account.setRoles(StringUtils.stringToSet(roleId));
		accountService.update(account);
		return redirect(ResultPath.account);
	}

	@RequestMapping("/state/{id}")
	public String state(@PathVariable long id) {
		logger.debug("state: id[{}]", id);
		Account account = accountService.get(id);
		account.setStatus(!BooleanUtils.isTrue(account.getStatus()));
		accountService.update(account);
		return redirect(ResultPath.account);
	}

	@ModelAttribute("account")
	public Account getAccount(HttpServletRequest request) {
		return (Account) prepare(request, Account.class);
	}

	@Override
	protected Object get(Long id) {
		return accountService.get(id);
	}

	@Override
	protected String getModule() {
		return Module.sys;
	}

	private AccountService accountService;
	private RoleService roleService;

	@Required
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Required
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
