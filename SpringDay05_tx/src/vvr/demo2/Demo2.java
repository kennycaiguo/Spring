package vvr.demo2;

/**
 * ʹ��ע�ⷽʽ��������
 */
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class Demo2 {

	@Resource(name="accountService")
	private AccountService accountService;
	
	@Test
	public void run() {
		
		accountService.pay("����", "�Ǻ�", 1000);
	}
}
