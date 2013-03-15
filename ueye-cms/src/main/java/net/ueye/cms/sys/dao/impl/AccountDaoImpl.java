package net.ueye.cms.sys.dao.impl;

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

}
