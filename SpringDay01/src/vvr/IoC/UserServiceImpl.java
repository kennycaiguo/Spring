package vvr.IoC;

public class UserServiceImpl implements UserService {

	//��ͨ���Ե�����ע��
	private String name;
	
	
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public void sayHello() {
		
		System.out.println("Hello Spring!!!" + name);
		
	}

}
