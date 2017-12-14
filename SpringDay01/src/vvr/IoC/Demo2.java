package vvr.IoC;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo2 {

	/**
	 * ʹ��Spring��IoC
	 */
	@Test
	public void run() {
		
		//��ȡ�����ļ���ClassPathXmlApplicationContext  ��ָ��·���µ��ļ�����src��
		//ʹ��Spring����
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//getBean��д���������ļ�����Ҫ��õĶ����id
		UserService us = (UserService) ac.getBean("userService");
		us.sayHello();
	}
}
