package vvr.demo4;

public class Person {

	//ͨ�����캯��ע�����
	
	private String name;
	private Car car;
	
	public Person(String name, Car car) {
		super();
		this.name = name;
		this.car = car;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", car=" + car + "]";
	};
	
	
}
