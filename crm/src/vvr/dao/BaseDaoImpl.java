package vvr.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import vvr.domain.Customer;
import vvr.domain.PageBean;

/**
 *ͨ�õ�dao�ӿ�ʵ���࣬����daoʵ����ֱ�Ӽ̳������Ϳ���
 * @author wwr
 *
 * @param <T>
 */
@SuppressWarnings("all")	//�������
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	
	//��������
	private Class clazz;
	
	//����ͨ���вεĹ��췽����ָ��clazz
	/*
	 * ���ַ�ʽ��Ҫ�������е��ø���Ĺ��췽��������һ�����󣬵������ַ�ʽ�ܺ����
	 * public BaseDaoImpl(Class clazz) {
		this.clazz = clazz;
	}
	*/
	
	//��һ�ַ�ʽ���ṩ�޲εĹ��췽��
	public BaseDaoImpl() {
		
		/*
		 * ͨ��spring���ص㣬�ڷ���������ʱ���ض��󣬴��������ͬʱ������ͬ��Ҳ�ᱻ���أ�ִ�и���Ĭ�ϵĹ��췽����ͨ�����ַ�ʽ�õ�����
		 * �������CustomerDaoImplʱ���̳е�BaseDaoImplҲ�ᱻ���أ�ִ��BaseDaoImpl��Ĭ�Ϲ��췽��
		 */
		
		//����Ŀ�ģ��õ�CustomerDaoImpl extends BaseDaoImpl<Customer>�е�Customer
		
		//�˴���this��ʾ���࣬�����Ǽ�������˴����ĸ��࣬����this��ʾ����
		//c��ʾCustomerDaoImpl��Class����
		//��һ��
		Class c = this.getClass();
		
		//�ڶ���,��ȡ��BaseDaoImpl<Customer>
		Type type = c.getGenericSuperclass();	//�÷����õ�����
		
		//Ŀ�ģ���typeת�����ӽӿ�
		if(type instanceof ParameterizedType) {
			//ParameterizedType ���ӽӿ��еķ��������ĳ�����
			ParameterizedType ptype = (ParameterizedType) type;
			
			//��ȡCustomer,���ص���һ�����飬��Ϊ���Ϳ����ж��������Map<k,v>
			Type[] types = ptype.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}
		
	}
	
	
	
	/**
	 * ���
	 */
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	/**
	 * ɾ��
	 */
	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	/**
	 * �޸�
	 */
	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T findById(Long id) {
		
		//clazz.getSimpleName()�õ����������
		T t = (T) this.getHibernateTemplate().get(clazz, id);
		return t;
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());
	}
	
	
	@Override
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		PageBean<T> page = new PageBean<T>();
		
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		//ʹ�þۺϺ�������ѯ���Լ�¼�� sql��䣺select count(*) from ...
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() != 0 ) {
			int totalCount = list.get(0).intValue();
			//�ܼ�¼��
			page.setTotalCount(totalCount);
		}
		
		//��ѯ��ҳ���ݣ���Ҫ�ѾۺϺ���ɾ��������Ϊ�վͿ���
		//��ʱsql��䣺select * from ...
		criteria.setProjection(null);
		List<T> beanList = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);
		
		//��ҳ����
		page.setBeanList(beanList);
		
		return page;
	}

}
