package vvr.demo2;

public interface AccountDao {

	/**
	 * ��Ǯ����
	 * @param out
	 * @param money
	 */
	public void outMoney(String out,double money);
	
	/**
	 * ��Ǯ��
	 * @param in
	 * @param money
	 */
	public void inMoney(String in,double money);
}
