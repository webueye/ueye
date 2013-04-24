package net.ueye.cms;

import org.joda.time.DateTime;

/**
 * @author rubys
 * @since 2013-3-15
 */
public class MainClass {

	public static void main(String[] args) {

		p(DateTime.parse("2013-04-25T02:50:24.76-05:00").toLocalDateTime());

	}

	String p() {
		return "";
	}
	
	String p(String a, String b, String c) {
		return "";
	}

	static void p(Object value) {
		System.out.println(value);
	}

}
