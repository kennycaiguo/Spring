package vvr.demo2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
	
	/*��һ�ַ�����ע��jdbcTemplate
	 * private JdbcTemplate jdbcTemplate;
	
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/

	@Override
	public void outMoney(String out, double money) {
		
		//jdbcTemplate.update(sql, args);
		
		/*
		 *�ڶ��ַ������̳� JdbcDaoSupport��
		 *��һ�ַ����б�ע�͵Ĵ����ڸ����д���
		 */
		//this.getJdbcTemplate().update(sql, args);
		
		/*
		 * �����ַ���(���շ���)
		 * ����Ҫ�������ļ���ע��jdbcTemplate,����ע��dataSource
		 * ��Ϊ�����д���һ����������jdbcģ��Ϊnullʱ����ͨ��dataSource����һ��jdbcģ��
		 */
		this.getJdbcTemplate().update("update t_account set money = money - ? where name = ?",money,out);
		
	}

	@Override
	public void inMoney(String in, double money) {
		
		this.getJdbcTemplate().update("update t_account set money = money + ? where name = ?",money,in);

	}

}
