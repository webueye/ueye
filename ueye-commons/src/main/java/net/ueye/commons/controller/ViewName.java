package net.ueye.commons.controller;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-20
 */
public enum ViewName {

	list("-list"), insert("-insert"), show("-show"), edit("-edit"), redirect("redirect:");

	private String value;

	private ViewName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
