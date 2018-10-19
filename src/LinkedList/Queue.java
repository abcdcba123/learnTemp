package LinkedList;

public interface Queue<E> {

	int getSize();

	boolean isEmpty();

	//入队
	void push(E e);

	// 出队
	E pop();

	// 看一眼队首
	E peek();
}
