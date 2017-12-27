package vvr.jdbc.demo;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Spring��jdbc��ģ����
 * @author wwr
 *
 */
public class Demo1 {

	/**
	 * ��ʹ��new�ķ�����ʾ
	 */
	@Test
	public void run() {
		
		//JDBCģ����
		JdbcTemplate template = new JdbcTemplate();
		
		//��ȡSpring���ṩ�����ӳ�
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_day03?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		//�������ӳ�
		template.setDataSource(dataSource);
		
		template.update("insert into t_account(name,money) values(?,?)", "����",100000);
	}
}
