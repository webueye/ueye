package net.ueye.cms.commons.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.LocalDate;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-21
 */
@SuppressWarnings("serial")
public class DatePlus extends TagSupport {

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			if (StringUtils.isNotBlank(date) && NumberUtils.isNumber(plusDays)) {
				out.print(new LocalDate(date).plusDays(
						Integer.parseInt(plusDays)).toString("yyyy-MM-dd"));
			} else {
				out.print(date);
			}
		} catch (IOException e) {
			try {
				out.print("");
			} catch (Exception ee) {

			}
		}
		return EVAL_PAGE;
	}

	private String date;
	private String plusDays;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setPlusDays(String plusDays) {
		this.plusDays = plusDays;
	}

	public String getPlusDays() {
		return plusDays;
	}

}
