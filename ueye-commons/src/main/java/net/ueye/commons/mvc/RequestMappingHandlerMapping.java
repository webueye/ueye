package net.ueye.commons.mvc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils.MethodFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethodSelector;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.util.UrlPathHelper;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @author rubys@vip.qq.com
 * @since 2013-3-4
 */
public class RequestMappingHandlerMapping extends org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping {

	@Override
	protected void detectHandlerMethods(final Object handler) {
		Class<?> handlerType = (handler instanceof String) ? getApplicationContext().getType((String) handler) : handler.getClass();

		final Class<?> userType = ClassUtils.getUserClass(handlerType);

		Set<Method> methods = HandlerMethodSelector.selectMethods(userType, new MethodFilter() {
			@Override
			public boolean matches(Method method) {
				return getMappingForMethod(method, userType) != null;
			}
		});

		Set<String> methodNames = Sets.newHashSet();
		for (Method method : methods) {
			methodNames.add(method.getName());

			RequestMappingInfo mapping = getMappingForMethod(method, userType);
			registerHandlerMethod(handler, method, mapping);
		}

		final List<String> exts = Arrays.asList("list", "index", "create", "show", "edit", "editNew", "destroy", "update");

		Set<Method> extMethods = HandlerMethodSelector.selectMethods(userType, new MethodFilter() {
			@Override
			public boolean matches(Method method) {
				return getMappingForMethod(method, userType) == null && exts.contains(method.getName());
			}
		});

		Map<String, Method> extMethodMap = Maps.newHashMap();
		for (Method method : extMethods) {
			String methodName = method.getName();
			if (methodNames.contains(methodName)) {
				continue;
			}

			if (!String.class.getSimpleName().equals(method.getReturnType().getSimpleName())) {
				continue;
			}

			Method m = extMethodMap.get(methodName);
			if (m == null) {
				extMethodMap.put(methodName, method);
			} else {
				if (method.getParameterTypes().length > m.getParameterTypes().length) {
					extMethodMap.put(methodName, method);
				}
			}
		}

		RequestMapping typeAnnotation = AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
		Map<String, RequestMappingInfo> map = getMappingForMethods(handlerType);
		for (Method method : extMethodMap.values()) {

			RequestMappingInfo mapping = map.get(method.getName());
			if (typeAnnotation != null) {
				mapping = createRequestMappingInfo(typeAnnotation.value(), null).combine(mapping);
			}
			registerHandlerMethod(handler, method, mapping);
		}

	}

	private Map<String, RequestMappingInfo> getMappingForMethods(Class<?> handlerType) {
		RequestCondition<?> condition = getCustomTypeCondition(handlerType);
		Map<String, RequestMappingInfo> mapping = new HashMap<String, RequestMappingInfo>();
		mapping.put("index", createRequestMappingInfo(new String[] { "" }, condition, RequestMethod.GET));
		mapping.put("create", createRequestMappingInfo(new String[] { "" }, condition, RequestMethod.POST));
		mapping.put("list", createRequestMappingInfo(new String[] { "list" }, condition, RequestMethod.GET, RequestMethod.POST));
		mapping.put("update", createRequestMappingInfo(new String[] { "update", "update/{id}" }, condition, RequestMethod.POST));
		mapping.put("destroy", createRequestMappingInfo(new String[] { "destroy/{id}" }, condition, RequestMethod.GET));
		mapping.put("show", createRequestMappingInfo(new String[] { "show/{id}" }, condition, RequestMethod.GET));
		mapping.put("edit", createRequestMappingInfo(new String[] { "edit/{id}" }, condition, RequestMethod.GET));
		mapping.put("editNew", createRequestMappingInfo(new String[] { "new", "edit-new" }, condition, RequestMethod.GET));
		return mapping;
	}

	private RequestMappingInfo createRequestMappingInfo(String[] patterns, RequestCondition<?> customCondition, RequestMethod... requestMethods) {
		return new RequestMappingInfo(new PatternsRequestCondition(patterns, new UrlPathHelper(), new AntPathMatcher(), true, true,
				new ArrayList<String>()), new RequestMethodsRequestCondition(requestMethods), new ParamsRequestCondition(),
				new HeadersRequestCondition(), new ConsumesRequestCondition(), new ProducesRequestCondition(), customCondition);
	}

}
