package net.ueye.commons.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
public class StringUtils {

	public static final String COMMA = ",";

	public static List<String> stringTokenizer(String value) {
		List<String> results = Lists.newArrayList();
		StringTokenizer st = new StringTokenizer(value);
		while (st.hasMoreElements()) {
			String str = st.nextElement().toString();
			if (org.apache.commons.lang.StringUtils.isNotBlank(str)) {
				results.add(str);
			}
		}
		return results;
	}

	public static String collectionToString(Collection<Object> collection) {
		if (CollectionUtils.isEmpty(collection)) {
			return "";
		} else {
			StringBuilder builder = new StringBuilder();
			for (Object obj : collection) {
				if (builder.length() != 0) {
					builder.append(COMMA).append(String.valueOf(obj));
				} else {
					builder.append(String.valueOf(obj));
				}
			}
			return builder.toString();
		}
	}

	public static Set<Long> stringToSet(String str) {
		Set<Long> result = Sets.newHashSet();
		if (org.apache.commons.lang.StringUtils.isBlank(str)) {
			return result;
		}
		for (String value : str.split(COMMA)) {
			result.add(Long.valueOf(value));
		}
		return result;
	}

	public static Set<Long> stringToSet(String[] values) {
		Set<Long> result = Sets.newHashSet();
		if (values != null) {
			for (String value : values) {
				result.add(Long.valueOf(value));
			}
		}
		return result;
	}

}
