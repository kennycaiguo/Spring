package vvr.aopanno.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * ʹ��ע�ⷽʽ���AOP
 * @author wwr
 *
 */
@Aspect
public class MyAspectAnno {

	/**
	 * ����֪ͨ��value��������д�������ʽ
	 */
	@Before(value="MyAspectAnno.fn()")
	public void log() {
		System.out.println("��¼��־...");
	}
	
	/**
	 * ����֪ͨ
	 * @param joinPoint
	 */
	@Around(value="MyAspectAnno.fn()")
	public void around(ProceedingJoinPoint joinPoint) {
		System.out.println("����֪ͨ1...");
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("����֪ͨ2...");
	}
	
	@Pointcut(value="execution(* *..*.*DaoImpl.save*(..))")
	public void fn() {}
}
