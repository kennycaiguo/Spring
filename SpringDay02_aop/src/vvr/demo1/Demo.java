package vvr.demo1;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ����AOP����
 * @author wwr
 *������save����ִ��ִ��֮ǰ��ӡһ����䡣ʹ��AOPʵ��
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo {

	@Resource(name="customerDao")
	private CustomerDao cDao;
	
	
	@Test
	public void run() {
		
		cDao.save();
		//cDao.update();
	}
}
