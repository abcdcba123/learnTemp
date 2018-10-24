package PriorityQueue;

public class PriorityQueue<E> {

	private E[] data;
	private int size;

	// 构造函数，传入数组的容量capacity构造Array
	public PriorityQueue(int capacity) {
		data = (E[]) new Object[capacity];
		size = 0;
	}
}
