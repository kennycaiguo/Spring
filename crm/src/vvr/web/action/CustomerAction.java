package vvr.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import vvr.domain.Customer;
import vvr.domain.PageBean;
import vvr.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>,RequestAware{

	/**
	 * �ͻ��߼���
	 */
	private static final long serialVersionUID = 1796491760814489483L;
	
	//ʹ��ģ��������װ���ݣ���Ҫnew������
	private Customer customer = new Customer();
	private CustomerService customerService;
	
	//��ǰҳ
	private Integer pageCode = 1;
	
	//һҳ��ʾ����������
	private Integer pageSize = 2;
	
	private Map<String,Object> request;
	
	
	
	public void setPageCode(Integer pageCode) {
		
		if(pageCode == null) {
			pageCode = 1;
		}
		
		this.pageCode = pageCode;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public Customer getModel() {
		return customer;
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;	
	}
	
	/**
	 * ����ͻ��ķ���
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		
		customerService.save(customer);
		
		return NONE;
	}
	
	
	/**
	 * �ṩ�ͻ���ҳ����
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception{
		
		//��ѯ������Ĭ��û������
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		PageBean<Customer> page = customerService.findByPage(pageCode,pageSize,criteria);
		
		request.put("page", page);
		
		System.out.println("----------" + page.getTotalCount() + "   " + page.getPageCode());
		
		return "page";
	}


}
