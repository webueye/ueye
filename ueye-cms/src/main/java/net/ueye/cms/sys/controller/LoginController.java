package net.ueye.cms.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.ueye.cms.sys.controller.path.ResultPath;
import net.ueye.cms.sys.entity.Account;
import net.ueye.cms.sys.service.AccountService;
import net.ueye.commons.bean.Page;
import net.ueye.commons.controller.CommonController;
import net.ueye.commons.orm.PropertyFilter;
import net.ueye.commons.util.EncodeUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@Controller
@RequestMapping(ResultPath.login)
public class LoginController extends CommonController {

	@RequestMapping
	public String index() {
		return redirect("/main/login.jsp");
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpServletRequest request, Account account, RedirectAttributes redirectAttributes) {
		logger.debug("login: account[{}]", account);
		HttpSession session = request.getSession();

		PropertyFilter username = new PropertyFilter("EQS_username", account.getUsername());
		List<PropertyFilter> filters = Lists.newArrayList(username);

		List<Account> accounts = accountService.findPage(new Page(), filters);
		if (CollectionUtils.isEmpty(accounts)) {
			redirectAttributes.addFlashAttribute("msg", "accountNotExist");
			return redirect("/login");
		}
		Account acc = accounts.get(0);
		if (!acc.getPassword().equals(EncodeUtils.md5(account.getPassword()))) {
			redirectAttributes.addFlashAttribute("msg", "passwordNotCorrect");
			return redirect("/login");
		}
		if (BooleanUtils.isFalse(acc.getStatus())) {
			redirectAttributes.addFlashAttribute("msg", "accountInactive");
			return redirect("/login");
		}
		session.setAttribute("currentAccount", acc);
		return redirect("/main/index.jsp");
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return redirect(ResultPath.login);
	}

	@Override
	protected String getModule() {
		// TODO Auto-generated method stub
		return null;
	}

	private AccountService accountService;

	@Required
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
