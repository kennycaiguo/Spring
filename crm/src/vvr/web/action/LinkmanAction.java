package vvr.web.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import vvr.domain.Customer;
import vvr.domain.Linkman;
import vvr.domain.PageBean;
import vvr.service.LinkmanService;

/**
 * ��ϵ�˿����߼���
 * @author wwr
 *
 */
public class LinkmanAction extends ActionSupport implements RequestAware{

	private static final long serialVersionUID = -1576267734510388727L;
	
	private Linkman linkman = new Linkman();
	
	private LinkmanService linkmanService;
	
	private Integer pageCode = 1;
	private Integer pageSize = 2;
	
	private Map<String,Object> request;


	public Integer getPageCode() {
		return pageCode;
	}

	public void setPageCode(Integer pageCode) {
		if(pageCode == null) {
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}

	public Linkman getLinkman() {
		return linkman;
	}

	public void setLinkman(Linkman linkman) {
		this.linkman = linkman;
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	
	/**
	 * ��ҳ��ѯ��ϵ��
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception{
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		
		//ƴ�Ӳ�ѯ����,��ȡ��ϵ������
		String lkmName = linkman.getLkm_name();
		if(lkmName != null && !lkmName.trim().isEmpty()) {
			criteria.add(Restrictions.like("lkm_name", "%" + lkmName + "%"));
		}
		
		//��ȡ�ͻ�
		Customer c = linkman.getCustomer();
		if(c != null && c.getCust_id() != null) {
			criteria.add(Restrictions.eq("customer.cust_id", c.getCust_id()));
		}
		
		PageBean<Linkman> page = linkmanService.findByPage(this.pageCode,this.pageSize,criteria);
		
		request.put("page", page);
		
		return "page";
	}
	
	/**
	 * ��ʼ����ϵ����ӽ���
	 * @return
	 * @throws Exception
	 */
	public String initAddUI() throws Exception{
		
		return "init";
	}
	
	/**
	 * ��ϵ�����
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		
		linkmanService.save(linkman);
		
		return "save";
	}
	
	
	/**
	 * ɾ����ϵ��
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		
		linkman = linkmanService.findById(linkman.getLkm_id());
		linkmanService.delete(linkman);
		
		return "delete";
	}
	
	
	/**
	 * ��ʼ����ϵ���޸�
	 * @return
	 * @throws Exception
	 */
	public String initUpdate() throws Exception{
		
		linkman = linkmanService.findById(linkman.getLkm_id());
		request.put("linkman", linkman);
		
		return "initUpdate";
	}
	
	/**
	 * ��ϵ���޸�
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception{
		
		linkmanService.update(linkman);
		
		return "update";
	}

}
