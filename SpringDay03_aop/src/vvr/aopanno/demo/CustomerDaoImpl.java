package vvr.aopanno.demo;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void save() {
		
		System.out.println("����ͻ�...");
		
	}

	@Override
	public void update() {

		System.out.println("�޸Ŀͻ�...");
	}

}
