package net.ueye.cms.commons.controller;

import javax.servlet.http.HttpServletRequest;

import net.ueye.cms.sys.entity.Account;
import net.ueye.commons.bean.Page;
import net.ueye.commons.controller.ViewName;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-25
 */
@SessionAttributes("currentAccount")
public abstract class CommonController extends net.ueye.commons.controller.CommonController {

	public String index(HttpServletRequest request, Page page) {
		return list(request, page);
	}

	public String list(HttpServletRequest request, Page page) {
		return forward(ViewName.list);
	}

	protected Account getAccount(Model model) {
		Object value = model.asMap().get("currentAccount");
		if (value != null && value instanceof Account) {
			return (Account) value;
		}
		throw new IllegalStateException();
	}

	public Object prepare(HttpServletRequest request, Class<?> clazz) {
		String requestURI = request.getRequestURI();
		Long id = extractId(requestURI);
		if (id == null) {
			try {
				return clazz.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		return get(id);
	}

	protected abstract Object get(Long id);

}
