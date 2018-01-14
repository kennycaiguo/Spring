package vvr.web.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import vvr.domain.User;
import vvr.service.UserService;

/**
 * �û����Ʋ�
 * @author wwr
 *
 */
public class UserAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 8330439462047722070L;
	
	private UserService userService;
	private User user;
	private Map<String,Object> session;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	 * �û�ע��
	 * @return
	 * @throws Exception
	 */
	public String regist() throws Exception{
		
		userService.save(user);
		
		return LOGIN;
	}
	
	/**
	 * ��¼����֤����ѯ���ݿ����Ƿ����
	 * @return
	 * @throws Exception
	 */
	public String checkCode() throws Exception{
		
		User u = userService.checkCode(user.getUser_code());
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if(u != null) {
			writer.print("no");
		}else {
			writer.println("yes");
		}
		
		return NONE;
	}
	
	/**
	 * ��¼
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception{
		
		User u = userService.login(user);
		if(u == null) {
			//����û�Ϊ��
			return LOGIN;
		}else {
			//��¼�ɹ�
			session.put("user", u);
			return "loginOK";
		}
	}
	
	/**
	 * �û��˳�
	 * @return
	 * @throws Exception
	 */
	public String exit() throws Exception{
		
		session.remove("user");
		
		return LOGIN;
	}
	
}
