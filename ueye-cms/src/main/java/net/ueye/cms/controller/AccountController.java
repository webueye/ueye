package net.ueye.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rubys@vip.qq.com
 * @since 2013-2-27
 */
@Controller
@RequestMapping("account")
public class AccountController {

	@RequestMapping
	public String index() {

		return "index";
	}

}
