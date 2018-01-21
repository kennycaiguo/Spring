package vvr.dao;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import vvr.domain.Customer;
import vvr.domain.PageBean;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

/*  ����̳���HibernateDaoSupport,�Ͳ���д���´��룬������JdbcDaoSupport
 * 	private HibernateTemplate hibernateTemplate;
	
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}*/


	@Override
	public void save(Customer customer) {
		
		this.getHibernateTemplate().save(customer);
		
	}

	/**
	 * ��ҳ��ѯ
	 */
	@Override
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setPageCode(pageCode);
		pageBean.setPageSize(pageSize);
		
		
		//�Ȳ�ѯ�ܼ�¼��������ó���ҳ��   select count(*)
		criteria.setProjection(Projections.rowCount());	//������ѯ����ѯ�����ж���������
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0) {
			int totalCount = list.get(0).intValue();
			//�ܼ�¼��
			pageBean.setTotalCount(totalCount);
		}
		
		//��ҳ��ѯǰ���Ƚ�������Ϊnull����Ϊselect *���������Ϊ�գ�����select count(*)
		criteria.setProjection(null);
		
		//��ҳ��ѯ  select * from �� limit ?,?
		//Spring��װ��hibernate�ķ�ҳ��ѯ�����Ŀ�ʼ�飬�����������
		List<Customer> beanList = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);
		
		
		//��װ��PageBean��
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * ��ѯ��ָ���ͻ�����Ϣ
	 */
	@Override
	public Customer findById(Long cust_id) {
		
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}

	@Override
	public void delete(Customer customer) {
		
		this.getHibernateTemplate().delete(customer);
	}

}
