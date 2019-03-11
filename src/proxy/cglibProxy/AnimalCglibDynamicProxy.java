package proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import proxy.Animal;
import proxy.Dog;

import java.lang.reflect.Method;

public class AnimalCglibDynamicProxy implements MethodInterceptor {

	/**
	 * 在enhancer中有一个setCallBack(this)
	 * 这样就实现了代理类和委托类的关联
	 */
	private Enhancer enhancer = new Enhancer();

	/**
	 * 创建代理类对象
	 */
	public Object createProxy(Class clazz) {
		//设置公共的接口或者公共的类
		enhancer.setSuperclass(clazz);
		//建立关联关系
		enhancer.setCallback(this);
		return enhancer.create();
	}

	/**
	 * 类似于我们jdk中的invoke
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("主人在召唤"); //系统级业务   开始事务
		Object result = proxy.invokeSuper(obj, args);  // 主业务
		System.out.println("主人离开"); //系统级业务     日志处理  关闭事务
		return result;
	}

	//创建测试方法
	public static void main(String[] args) {
		AnimalCglibDynamicProxy proxy = new AnimalCglibDynamicProxy();
		//这里的参数可以传三种形式01：new Dog().getClass()
		// 02：Class.forName("cn.pb.dao.impl.Dog") 03.Dog.class
		Animal dog = (Animal) proxy.createProxy(Dog.class);
		dog.eat();
		System.out.println("**************************");
		dog.sleep();
	}
}
