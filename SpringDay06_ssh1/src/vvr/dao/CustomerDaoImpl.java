package vvr.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import vvr.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

/*  ����̳���HibernateDaoSupport,�Ͳ���д���´��룬������JdbcDaoSupport
 * 	private HibernateTemplate hibernateTemplate;
	
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}*/


	@Override
	public void save(Customer customer) {
		
		System.out.println("�־ò�...");
		//hibernateTemplate.save(customer);
		this.getHibernateTemplate().save(customer);
		
	}

}
