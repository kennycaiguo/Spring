package vvr.jdbc.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * ʹ��IOC��ʽʹ��JDBCģ����
 * @author wwr
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Demo1_1 {

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * �������
	 */
	@Test
	public void save() {
		
		jdbcTemplate.update("insert into t_account(name,money) values(?,?)", "����",100000);
	}
	
	/**
	 * �޸Ĳ���
	 */
	@Test
	public void update() {
		jdbcTemplate.update("update t_account set name=? where id=?", "����",4);
	}
	
	/**
	 * ɾ������
	 */
	@Test
	public void delete() {
		jdbcTemplate.update("delete from t_account where id=?", 4);
	}
	
	/**
	 * ��ѯ��������
	 */
	@Test
	public void queryOne() {
		//rowMapper��һ���ӿڣ���Ҫ�Լ����ʵ����
		Account ac = jdbcTemplate.queryForObject("select * from t_account where id=?", new BeanMapper(), 1);
		System.out.println(ac);
	}
	
	/**
	 * ��ѯȫ������
	 */
	@Test
	public void queryAll() {
		List<Account> list = jdbcTemplate.query("select * from t_account", new BeanMapper());
		System.out.println(list);
	}
}

/**
 * �ֶ���дʵ���࣬rowMapper��һ��һ�з�װ���ݣ�����Ҫʹ��ѭ��
 * @author wwr
 *
 */
class BeanMapper implements RowMapper<Account>{

	@Override
	public Account mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Account ac = new Account();
		ac.setId(rs.getInt("id"));
		ac.setName(rs.getString("name"));
		ac.setMoney(rs.getDouble("money"));
		
		return ac;
	}
	
}
