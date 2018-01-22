package vvr.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import vvr.domain.Customer;
import vvr.domain.PageBean;

/**
 * �־ò�
 * @author wwr
 *
 */
public interface CustomerDao {

	/**
	 * ����ͻ�
	 * @param customer
	 */
	public void save(Customer customer);

	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

	public Customer findById(Long cust_id);

	public void delete(Customer customer);

	public void update(Customer customer);
}
