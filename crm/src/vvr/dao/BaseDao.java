package vvr.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import vvr.domain.PageBean;

/**
 * ��дͨ�õ�dao�ӿڣ����峣�õķ���
 * @author wwr
 *�Զ��巺�ͣ����ڱ�����󣬶��ڷ�����Ҳ���Դ���Object�����Ƿ����ܺ�һ��
 *
 *����dao�ӿ�ֱ�Ӽ̳�����ӿ�
 */
public interface BaseDao<T> {

	/**
	 * ���
	 */
	public void save(T t);
	
	/**
	 * ɾ��
	 * @param t  t������Ķ���
	 */
	public void delete(T t);
	
	/**
	 * �޸�
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * ��ѯָ����ŵ�����
	 * @param id
	 * @return
	 */
	public T findById(Long id);
	
	public List<T> findAll();
	
	/**
	 * ��ҳ�����ص���ͨ�õķ�ҳ����
	 * @return
	 */
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
	
}
