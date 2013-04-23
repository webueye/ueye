package net.ueye.cms.server;

import javax.servlet.Filter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author rubys
 * @since 2013-3-15
 */
public class MainClass {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app-shiro.xml");
		
		Object obj = (ctx.getBean("shiro", Filter.class));
		
		p(obj instanceof Filter);
	}
	
	static void p(Object value){
		System.out.println(value);
	}

}
