package vvr.service;

import org.springframework.transaction.annotation.Transactional;

import vvr.dao.UserDao;
import vvr.domain.User;
import vvr.web.utils.MD5Utils;
//����
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * ��֤��¼���Ƿ����
	 */
	@Override
	public User checkCode(String user_code) {
		return userDao.checkCode(user_code);
	}

	/**
	 * �����û�
	 */
	@Override
	public void save(User user) {
		
		String pwd = user.getUser_password();
		//�������
		user.setUser_password(MD5Utils.md5(pwd));
		
		//���õ�¼״̬��Ĭ��Ϊ1
		user.setUser_state("1");
		
		userDao.save(user);
	}

	/**
	 * �û���¼����֤��¼��������
	 * ������Ҫ���ܺ��ٲ�ѯ
	 */
	@Override
	public User login(User user) {
		
		String pwd = user.getUser_password();
		user.setUser_password(MD5Utils.md5(pwd));
		
		return userDao.login(user);
	}
	
}
