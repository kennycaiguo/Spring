package vvr.domain;

/**
 * �ͻ��ݷõ�javabean
 * @author wwr
 *
 */
public class Visit {
	
	/**
	 * `visit_id` VARCHAR(32) NOT NULL,
  `visit_cust_id` BIGINT(32) DEFAULT NULL COMMENT '�ͻ�id',
  `visit_user_id` BIGINT(32) DEFAULT NULL COMMENT '������id',
  `visit_time` VARCHAR(32) DEFAULT NULL COMMENT '�ݷ�ʱ��',
  `visit_interviewee` VARCHAR(32) DEFAULT NULL COMMENT '���ݷ���',
  `visit_addr` VARCHAR(128) DEFAULT NULL COMMENT '�ݷõص�',
  `visit_detail` VARCHAR(256) DEFAULT NULL COMMENT '�ݷ�����',
  `visit_nexttime` VARCHAR(32) DEFAULT NULL COMMENT '�´ΰݷ�ʱ��',
	 * 
	 */
	
	// ����
	private String visit_id;
	// �ݷ�ʱ��
	private String visit_time;
	// ���ݷõ���
	private String visit_interviewee;
	// �ݷõص�
	private String visit_addr;
	// �ݷ�����
	private String visit_detail;
	// ��һ�εİݷ�ʱ��
	private String visit_nexttime;
	
	// ������Ϳͻ�
	private Customer customer;
	// ��������û�
	private User user;
	
	public String getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(String visit_id) {
		this.visit_id = visit_id;
	}
	public String getVisit_time() {
		return visit_time;
	}
	public void setVisit_time(String visit_time) {
		this.visit_time = visit_time;
	}
	public String getVisit_interviewee() {
		return visit_interviewee;
	}
	public void setVisit_interviewee(String visit_interviewee) {
		this.visit_interviewee = visit_interviewee;
	}
	public String getVisit_addr() {
		return visit_addr;
	}
	public void setVisit_addr(String visit_addr) {
		this.visit_addr = visit_addr;
	}
	public String getVisit_detail() {
		return visit_detail;
	}
	public void setVisit_detail(String visit_detail) {
		this.visit_detail = visit_detail;
	}
	public String getVisit_nexttime() {
		return visit_nexttime;
	}
	public void setVisit_nexttime(String visit_nexttime) {
		this.visit_nexttime = visit_nexttime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

