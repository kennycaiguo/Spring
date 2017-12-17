package vvr.spring.demo1;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author wwr
 *
 *@Component(value="userService") �ȼ���  <bean id="userService" class="vvr.spring.demo1.UserServiceImpl">
 *���ע�⣬�����
 */
@Component(value="userService")
public class UserServiceImpl implements UserService {

	/*
	 * ����ע�룬@Value ����ע������������ͺ�String����
	 * ʹ��ע��ע�벻���ṩsetter����
	 */
	@Value(value="����")
	private String name;
	
	/*
	 * ע������
	 */
	@Resource(name="userDao")
	private UserDao userDao;
	
	
	/*	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setName(String name) {
		this.name = name;
	}
*/
	
	@Override
	public void sayHello() {
		
		System.out.println("Hello Spring!!!" + name);
		userDao.save();

	}

}
