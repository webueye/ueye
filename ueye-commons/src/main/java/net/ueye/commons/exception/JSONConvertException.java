package net.ueye.commons.exception;

import java.io.Serializable;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-25
 */
@SuppressWarnings("serial")
public class JSONConvertException extends RuntimeException implements Serializable {

	public JSONConvertException() {
		super();
	}

	public JSONConvertException(String message) {
		super(message);
	}

	public JSONConvertException(Throwable cause) {
		super(cause);
	}

}
