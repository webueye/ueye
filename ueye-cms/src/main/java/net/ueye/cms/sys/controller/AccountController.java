package net.ueye.cms.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.ueye.cms.Module;
import net.ueye.cms.commons.controller.CommonController;
import net.ueye.cms.sys.controller.path.Path;
import net.ueye.cms.sys.entity.Account;
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
@RequestMapping(Path.account)
public class AccountController extends CommonController {

	public String list(HttpServletRequest request, Page page) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		accountService.findPage(page, filters);
		return forward(ViewName.list);
	}

	public String editNew(Model model) {
		return forward(ViewName.insert);
	}

	public String create(Account account) {
		logger.debug("create: account[{}]", account);

		account.setPassword(EncodeUtils.md5(account.getPassword()));
		accountService.save(account);
		return redirect(Path.account);
	}

	public String edit(@PathVariable long id, Account account) {
		logger.debug("edit: id[{}], account[{}]", id, account);
		return forward(ViewName.edit);
	}

	public String update(Account account, String newPassword) {
		logger.debug("update: account[{}]", account);

		if (!org.apache.commons.lang.StringUtils.isBlank(newPassword)) {
			account.setPassword(EncodeUtils.md5(newPassword));
		}
		accountService.saveOrUpdate(account);

		return redirect(Path.account);
	}

	public String show(@PathVariable long id) {
		logger.debug("show: id[{}]", id);
		return forward(ViewName.show);
	}

	public String destroy(@PathVariable long id) {
		logger.debug("remove[{}]", id);

		accountService.delete(id);
		return redirect(Path.account);
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
		return redirect(Path.account);
	}

	@RequestMapping("/state/{id}")
	public String state(@PathVariable long id) {
		logger.debug("state: id[{}]", id);
		Account account = accountService.get(id);
		account.setStatus(!BooleanUtils.isTrue(account.getStatus()));
		accountService.update(account);
		return redirect(Path.account);
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
