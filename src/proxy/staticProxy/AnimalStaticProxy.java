package proxy.staticProxy;

import proxy.Animal;
import proxy.Dog;

/**
 * 狗狗的静态代理类
 */
public class AnimalStaticProxy implements Animal {

	private Dog dog;

	@Override
	public void eat() {
		System.out.println("主人在召唤"); //系统级业务
		dog.eat();
		System.out.println("主人离开"); //系统级业务
	}

	@Override
	public void sleep() {
		System.out.println("主人在召唤");  //系统级业务
		dog.sleep();
		System.out.println("主人离开"); //系统级业务
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}


	/**
	 * 我们发现的问题
	 * 01：代码冗余
	 * 02：把冗余的代码提取成公共的方法
	 * 03：有可能小猫咪也有这些方法
	 * 04：提取成一个工具类中的方法
	 * 05：现在有一个小猫咪 也需要执行 sleep和eat 以及系统级业务方法
	 * 06：我们又得创建一个小猫咪对应的代理类
	 * 07：动物有很多  ，难道需要我们创建N个代理类吗？？肯定！
	 */

	public static void main(String[] args) {
		AnimalStaticProxy proxy = new AnimalStaticProxy();
		Dog dog = new Dog();
		proxy.setDog(dog);
		proxy.eat();
		System.out.println("*************");
		proxy.sleep();
	}
}
