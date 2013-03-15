package net.ueye.commons.controller;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Sets;

/**
 * @author rubys@vip.qq.com
 * @since 2013-2-16
 */
public abstract class CommonController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected String show = "show";
	protected String edit = "edit";
	protected String update = "update";
	protected Set<String> methodMappings = Sets.newHashSet(show, edit, update);

	protected abstract String getModule();

	protected String forward(String viewName) {
		String path = this.getClass().getAnnotation(RequestMapping.class).value()[0];

		StringBuilder builder = new StringBuilder();
		builder.append(getModule());
		if (getModule() != null && !"/".equals(getModule().charAt(getModule().length() - 1))) {
			builder.append("/");
		}
		String controller = path.replaceAll("-", "");
		builder.append(controller);
		if (!"/".equals(controller.charAt(controller.length() - 1))) {
			builder.append("/");
		}
		builder.append(path).append(viewName);

		return builder.toString();
	}

	protected String forward(ViewName viewName) {
		return forward(viewName.getValue());
	}

	protected String redirect() {
		return ViewName.redirect.getValue() + this.getClass().getAnnotation(RequestMapping.class).value()[0];
	}

	protected String redirect(String action) {
		return ViewName.redirect.getValue() + action;
	}

	protected Long extractId(String requestURI) {
		for (String methodMapping : methodMappings) {
			methodMapping = "/" + methodMapping + "/";
			if (StringUtils.contains(requestURI, methodMapping)) {
				String id = requestURI.substring(requestURI.indexOf(methodMapping) + methodMapping.length());
				if (id.indexOf("/") != -1) {
					id = id.substring(0, id.indexOf("/"));
				}
				if (NumberUtils.isNumber(id)) {
					return Long.valueOf(id);
				}
			}
		}
		return null;
	}

	protected void addMethodMappings(Collection<String> methodMappings) {
		this.methodMappings.addAll(methodMappings);
	}

}
