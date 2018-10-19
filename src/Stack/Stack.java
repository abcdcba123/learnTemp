package Stack;

public interface Stack<E> {

	// 获取栈的size
	int getSize();

	// 查看栈是否为空
	boolean isEmpty();

	// 入栈
	void push(E e);

	// 出栈
	E pop();

	// 看看栈顶元素
	E peek();

}
