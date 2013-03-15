package net.ueye.cms.sys.controller;

import net.ueye.cms.Module;
import net.ueye.cms.sys.entity.Account;
import net.ueye.cms.sys.service.AccountService;
import net.ueye.commons.bean.Page;
import net.ueye.commons.controller.CommonController;
import net.ueye.commons.controller.ViewName;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rubys@vip.qq.com
 * @since 2013-2-27
 */
@RequestMapping(Path.ACCOUNT)
public class AccountController extends CommonController {

	private AccountService accountService;

	public String list(Page page) {
		accountService.findData(page);
		return forward(ViewName.list);
	}

	public String editNew() {
		return forward(ViewName.insert);
	}

	public String create(Account account) {
		accountService.save(account);
		return redirect();
	}

	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("account", accountService.get(id));
		return forward(ViewName.edit);
	}

	public String update(Account account) {
		accountService.update(account);
		return redirect();
	}

	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("account", accountService.get(id));
		return forward(ViewName.show);
	}

	public String destroy(@PathVariable Long id) {
		accountService.delete(id);
		return redirect();
	}

	@Override
	protected String getModule() {
		return Module.sys;
	}

	@Required
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
