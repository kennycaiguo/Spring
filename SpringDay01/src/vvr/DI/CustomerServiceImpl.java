package vvr.DI;

public class CustomerServiceImpl {

	//ʵ�ʿ����У�ҵ������������ڳ־ò�
	//Spring�о���Ҫ����ע�룬����Ҫnew����serviceдһ��dao�����ԣ���дset����
	private CustomerDaoImpl customerDao;
	
	
	public void setCustomerDao(CustomerDaoImpl customerDao) {
		this.customerDao = customerDao;
	}


	public void save() {
		System.out.println("����ҵ���service....");
		
		//��ʱ���г���ش���Ϊû��ʵ��������Ҫ�������ļ��м�������
		customerDao.save();
	}
}
