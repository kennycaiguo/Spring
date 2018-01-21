package vvr.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import vvr.domain.Customer;
import vvr.domain.PageBean;

/**
 * ҵ���߼���
 * @author wwr
 *
 */
public interface CustomerService {

	/**
	 * ����ͻ�
	 */
	public void save(Customer customer);

	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

	public Customer findById(Long cust_id);

	public void delte(Customer customer);
}
