package vvr.demo1;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * �����ࣺ����� +֪ͨ��֪ͨ�������ͣ�
 * �˴�д����֪ͨ����Ҫ��ʲô
 * @author wwr
 */
public class MyAspectXml {

	/**
	 * Ҫ���������һ�仰
	 */
	public void log() {
		System.out.println("��ӡ��־...");
	}
	
	/**
	 * ����֪ͨ������Ŀ�귽��ִ��֮����ִ�У���������쳣Ҳ��ִ��
	 */
	public void after() {
		System.out.println("����֪ͨ...");
	}
	
	/**
	 * ����֪ͨ������ִ��ǰ�ͷ���ִ�к����֪ͨ��Ĭ������£�Ŀ�����ķ�������ִ�У���Ҫ�ֶ���Ŀ�����ķ���ִ��
	 */
	public void around(ProceedingJoinPoint joinPoint) {
		
		System.out.println("����֪ͨ1...");
		
		try {
			//�ֶ�����Ŀ�귽�����̶�д��
			joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("����֪ͨ2...");
	}
}
