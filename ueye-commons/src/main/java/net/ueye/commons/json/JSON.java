package net.ueye.commons.json;

import java.io.IOException;
import java.io.StringWriter;

import net.ueye.commons.exception.JSONConvertException;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-25
 */
public class JSON {

	public static String toJSON(Object object) {
		StringWriter writer = null;
		try {
			writer = new StringWriter();
			JsonGenerator gen = new JsonFactory().createJsonGenerator(writer);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(gen, object);
			return writer.toString();
		} catch (IOException e) {
			throw new JSONConvertException(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e) {
					throw new JSONConvertException(e);
				}
			}
		}

	}

}
