package net.ueye.cms.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.ueye.cms.sys.dao.AccountDao;
import net.ueye.cms.sys.entity.Account;
import net.ueye.commons.orm.dao.HibernateDaoSupport;

/**
 * @author rubys
 * @since 2013-3-15
 */
@Repository
public class AccountDaoImpl extends HibernateDaoSupport<Account> implements AccountDao {
	
	@Override
	public Account findByUsername(String username) {
		List<Account> accounts = findDatas("username", username);
		if(!accounts.isEmpty()){
			return accounts.get(0);
		}
		return null;
	}

}
