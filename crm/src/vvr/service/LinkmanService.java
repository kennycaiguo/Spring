package vvr.service;

import org.hibernate.criterion.DetachedCriteria;

import vvr.domain.Linkman;
import vvr.domain.PageBean;

/**
 * ��ϵ��ҵ���߼���
 * @author wwr
 *
 */
public interface LinkmanService {

	PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

}
