package vvr.demo1;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;
	
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}


	/**
	 * ��ҵ���߼���ʹ������
	 */
	@Override
	public void pay(String out, String in, double money) {

		//��Ǯ
		accountDao.outMoney(out, money);
		
		//ģ���쳣
		//int num = 1/0;
		
		//��Ǯ
		accountDao.inMoney(in, money);
	}

}
