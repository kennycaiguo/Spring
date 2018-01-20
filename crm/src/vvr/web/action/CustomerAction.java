package vvr.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import vvr.domain.Customer;
import vvr.domain.Dict;
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
		
		/**
		 * ��������ѯ��ƴ������
		 */
		String cust_name = customer.getCust_name();
		//�������Ŀͻ����Ʋ�Ϊ�ղ��ҿͻ����Ʋ�Ϊ���ַ���
		if(cust_name != null && !cust_name.trim().isEmpty()) {
			criteria.add(Restrictions.like("cust_name", "%"+ cust_name +"%"));
		}
		
		Dict level = customer.getLevel();
		//����ͻ�����Ϊ�գ����Ҳ�Ϊ�յ��ַ���
		if(level != null && !level.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}
		
		Dict source = customer.getSource();
		//����ͻ���Դ��Ϊ�գ����Ҳ�Ϊ�յ��ַ���
		if(source != null && !source.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		
		PageBean<Customer> page = customerService.findByPage(pageCode,pageSize,criteria);
		
		request.put("page", page);
		
		//��ɰ�������ѯ�����ݻ��ԣ������ֶ������ݼ���ֵջ��Ҳ���Բ��ã���Ϊ�ڰ�������ѯʱ���Ѿ���ָ�����ݷ�װ
		//��ģ���������ˡ�ֵջ�л���CustomerAction������󣬸ö�������set��get���������ԲŻ���뵽ֵջ��
		//����customer��ģ���������أ�������jsp�������ͨ��model��ȡֵ����ʱ��model����customer
		
		
		return "page";
	}


}
