package vvr.web.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import vvr.domain.PageBean;
import vvr.domain.User;
import vvr.domain.Visit;
import vvr.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>,SessionAware,RequestAware{

	private static final long serialVersionUID = -6035351988762502185L;

	private VisitService visitService;
	private Visit visit = new Visit();
	private Map<String,Object> session;
	private Map<String,Object> request;
	//��ǰҳ
	private Integer pageCode = 1;
	
	//һҳ��ʾ����������
	private Integer pageSize = 2;
	
	private String beginDate;
	private String endDate;
	
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
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

	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	@Override
	public Visit getModel() {
		return visit;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	
	/**
	 * ��ӿͻ��ݷ�
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		
		//�Ȼ�ȡ����ǰ�û������õ��ݷü�¼�У��ٱ���
		User user = (User) session.get("user");
		if(user == null) {
			return LOGIN;
		}
		
		visit.setUser(user);
		
		visitService.save(visit);
		
		return "save";
	}
	
	/**
	 * �ͻ��ݷ÷�ҳ
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception{
		
		//��õ�¼���û�
		User user = (User) session.get("user");
		if(user == null) {
			return LOGIN;
		}
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		
		// ƴ�Ӳ�ѯ������
		if(beginDate != null && !beginDate.trim().isEmpty()){
			criteria.add(Restrictions.ge("visit_time", beginDate));
		}
		// select * from xxx where visit_time >= ? and visit_time <= ?
		if(endDate != null && !endDate.trim().isEmpty()){
			criteria.add(Restrictions.le("visit_time", endDate));
		}
		
		// ��Ӳ�ѯ����������ǰ��¼���û�ֻ�ܿ����Լ��İݷ��б�
		criteria.add(Restrictions.eq("user.user_id",user.getUser_id()));
		// ��ҳ��ѯ
		PageBean<Visit> page = visitService.findByPage(this.pageCode,this.pageSize,criteria);
		request.put("page", page);
		
		return "page";
	}

}
