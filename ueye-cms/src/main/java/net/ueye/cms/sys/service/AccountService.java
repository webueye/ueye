package net.ueye.cms.sys.service;

import net.ueye.cms.commons.service.BaseService;
import net.ueye.cms.sys.entity.Account;

/**
 * @author rubys
 * @since 2013-3-15
 */
public interface AccountService extends BaseService<Account> {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	static final int SALT_SIZE = 8;

	Account findByUsername(String username);

}
