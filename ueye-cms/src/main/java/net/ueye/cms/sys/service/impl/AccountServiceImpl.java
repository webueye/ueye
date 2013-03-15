package net.ueye.cms.sys.service.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import net.ueye.cms.commons.service.impl.BaseServiceImpl;
import net.ueye.cms.sys.dao.AccountDao;
import net.ueye.cms.sys.entity.Account;
import net.ueye.cms.sys.service.AccountService;
import net.ueye.commons.orm.dao.BaseDao;

/**
 * @author rubys
 * @since 2013-3-15
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

	private AccountDao accountDao;

	@Override
	public BaseDao<Account> getBaseDao() {
		return accountDao;
	}

	@Required
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

}
