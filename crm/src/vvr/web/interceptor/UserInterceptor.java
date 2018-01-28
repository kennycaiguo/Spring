package vvr.web.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import vvr.domain.User;

/**
 * �û������������ж��û��Ƿ��Ѿ���¼�������¼��ִ����һ����������
 * ���û�е�¼�����ص���¼ҳ�棨���ܶ����е��������أ�login��regist�����������أ�
 * �̳�ָ��������
 * @author wwr
 *
 */
public class UserInterceptor extends MethodFilterInterceptor implements SessionAware {

	private static final long serialVersionUID = -8421678755268452755L;
	private Map<String,Object> session;
	
	@Override
	public void setSession(Map<String, Object> session) {

		this.session = session;
	}

	/**
	 * ����Ŀ��Action�ķ���
	 */
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		/**
		 * ʹ������Map��ʽ��session�����׿��쳣��ԭ��������������������в���ʹ
		 * User user = (User) session.get("user");
		 */
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//���û�е�¼
		if(user == null){
			return "login";
		}
		// ִ����һ��������
		return invocation.invoke();
	}

}
