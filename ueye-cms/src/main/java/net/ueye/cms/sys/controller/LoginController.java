package net.ueye.cms.sys.controller;

import net.ueye.cms.Module;
import net.ueye.cms.sys.entity.Account;
import net.ueye.commons.controller.CommonController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rubys
 * @since 2013-3-23
 */
@Controller
@RequestMapping("login")
public class LoginController extends CommonController{
	
	@RequestMapping
	public String list(){
		
		return redirect("/main/login.jsp");
	}
	
	public String create(String username){
		
		return redirect("account");
	}

	@Override
	protected String getModule() {
		return Module.sys;
	}

}
