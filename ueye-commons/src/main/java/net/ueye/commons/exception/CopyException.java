package net.ueye.commons.exception;

import java.io.Serializable;

/**
 * @author rubys@vip.qq.com
 * @since 2012-8-25
 */
@SuppressWarnings("serial")
public class CopyException extends RuntimeException implements Serializable {

	public CopyException() {
		super();
	}

	public CopyException(String message) {
		super(message);
	}

	public CopyException(Throwable cause) {
		super(cause);
	}

}
