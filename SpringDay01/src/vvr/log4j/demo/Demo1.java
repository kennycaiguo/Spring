package vvr.log4j.demo;

import org.apache.log4j.Logger;
import org.junit.Test;

//��־��ʹ��
public class Demo1 {

	private Logger log = Logger.getLogger(Demo1.class);
	
	@Test
	public void run() {
		
		log.info("ִ����");
	}
}
