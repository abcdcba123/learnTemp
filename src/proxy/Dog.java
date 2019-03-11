package proxy;

import proxy.Animal;

/**
 * 狗狗类 实现了Animal接口
 */
public class Dog implements Animal {

	@Override
	public void eat() {
		System.out.println("狗狗在啃骨头！");
	}

	@Override
	public void sleep() {
		System.out.println("狗狗在睡觉！");
	}
}
