package vvr.service;

import org.springframework.transaction.annotation.Transactional;

import vvr.dao.CustomerDao;
import vvr.domain.Customer;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	@Override
	public void save(Customer customer) {
		
		System.out.println("ҵ���߼���...");
		customerDao.save(customer);

	}

}
