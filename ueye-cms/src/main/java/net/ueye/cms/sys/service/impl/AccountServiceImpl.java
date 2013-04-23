package net.ueye.cms.sys.service.impl;

import java.util.List;

import net.ueye.cms.sys.entity.Account;
import net.ueye.cms.sys.service.AccountService;
import net.ueye.commons.bean.Page;
import net.ueye.commons.orm.dao.HibernateDaoSupport;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-28
 */
@Transactional
@Service("accountService")
public class AccountServiceImpl extends HibernateDaoSupport<Account> implements
		AccountService {

	@Override
	public List<Account> findAccounts(Account account, Page page) {
		DetachedCriteria detachedCriteria = createDetachedCriteria();
		detachedCriteria.add(Example.create(account));
		return findPage(detachedCriteria, page);
	}

}
