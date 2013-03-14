package net.ueye.commons.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import net.ueye.commons.exception.CopyException;

import org.apache.commons.lang.ClassUtils;

import com.google.common.collect.Lists;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-23
 */
public class BeanUtils {

	public static void copyProperties(Object dest, Object orig) {
		try {
			for (Field origField : getFields(orig.getClass())) {
				origField.setAccessible(true);
				// Object origFieldValue = origField.get(orig);
				Object origValue = null;
				try {
					origValue = invokeReadMethod(orig, origField.getName(), orig.getClass());
				} catch (Exception e) {
					origValue = origField.get(orig);
				}
				if (origValue == null) {
					continue;
				}
				Field destField = ReflectionUtils.getAccessibleField(dest, origField.getName());
				if (destField == null || Modifier.isFinal(destField.getModifiers())) {
					continue;
				}

				destField.setAccessible(true);

				if (ClassUtils.getPackageName(origField.getType()).contains("entity")
						|| ClassUtils.getPackageName(origField.getType()).contains("model")) {
					Object value = destField.getType().newInstance();
					copyProperties(value, origValue);
					destField.set(dest, value);
				} else {
					destField.set(dest, origValue);
				}
			}
		} catch (Exception e) {
			throw new CopyException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Field> getFields(Class<?> clazz) {
		List<Field> fields = Lists.newArrayList(clazz.getDeclaredFields());
		List<Class<?>> classes = ClassUtils.getAllSuperclasses(clazz);
		for (Class<?> superClass : classes) {
			if (superClass != Object.class) {
				fields.addAll(Lists.newArrayList(superClass.getDeclaredFields()));
			}
		}
		return fields;
	}

	public static Object invokeReadMethod(Object bean, String propertyName, Class<?> beanClass) {
		try {
			PropertyDescriptor desc = new PropertyDescriptor(propertyName, beanClass);
			Method method = desc.getReadMethod();
			return method.invoke(bean);
		} catch (Exception e) {
			throw new CopyException(e);
		}
	}

}
