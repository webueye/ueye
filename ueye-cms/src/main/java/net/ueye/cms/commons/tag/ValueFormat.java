package net.ueye.cms.commons.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-17
 */
@SuppressWarnings("serial")
public class ValueFormat extends TagSupport {

	private String value;
	private String defaultValue = "--";
	private int length;

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			if (StringUtils.isBlank(value)) {
				out.print(defaultValue);
			} else if (length > 0) {
				out.print(value.substring(0, length) + "...");
			} else {
				out.print(value);
			}
		} catch (IOException e) {

		}
		return EVAL_PAGE;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
