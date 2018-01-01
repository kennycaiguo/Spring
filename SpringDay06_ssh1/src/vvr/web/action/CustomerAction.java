package vvr.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import vvr.domain.Customer;
import vvr.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	/**
	 * �ͻ��߼���
	 */
	private static final long serialVersionUID = 1796491760814489483L;
	
	//ʹ��ģ��������װ���ݣ���Ҫnew������
	private Customer customer = new Customer();
	private CustomerService customerService;
	
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}
	
	/**
	 * ����ͻ��ķ���
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		
		System.out.println("web��...");
		customerService.save(customer);
		
		return NONE;
	}

}
