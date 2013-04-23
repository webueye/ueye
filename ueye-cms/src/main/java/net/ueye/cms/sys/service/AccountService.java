package net.ueye.cms.sys.service;

import java.util.List;

import net.ueye.cms.sys.entity.Account;
import net.ueye.commons.bean.Page;
import net.ueye.commons.orm.dao.BaseDao;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
public interface AccountService extends BaseDao<Account> {

	List<Account> findAccounts(Account account, Page page);

}
