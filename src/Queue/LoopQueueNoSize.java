package Queue;

public class LoopQueueNoSize<E> implements Queue<E> {

	private E[] array;
	private int front, tail;

	public LoopQueueNoSize(int capacity) {
		array = (E[]) new Object[capacity + 1];
		front = 0;
		tail = 0;
	}

	public LoopQueueNoSize() {
		this(10);
	}

	@Override
	public int getSize() {
		int size;
		if (tail >= front) {
			size = tail - front;
		} else {
			size = tail + array.length - front;
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		return tail == front;
	}

	@Override
	public void push(E e) {
		// 扩容
		if ((tail + 1) % array.length == front) {
			resize(array.length * 2);
		}
		array[getSize()] = e;
		tail = (tail + 1) % this.array.length;
	}

	@Override
	public E pop() {
		if (isEmpty())
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

		E result = array[front];
		array[front] = null;
		front = (front + 1) % array.length;

		// 缩容
		if (getSize() <= array.length / 4 && array.length / 2 != 0) {
			resize(array.length / 2);
		}
		return result;
	}

	@Override
	public E peek() {
		if (isEmpty())
			throw new IllegalArgumentException("Queue is empty.");
		return array[front];
	}

	public void resize(int newCapacity) {
		E[] tempArray = (E[]) new Object[newCapacity + 1];
		for (int i = 0; i < getSize(); i++)
			tempArray[i] = array[(i + front) % array.length];
		tail = getSize();
		front = 0;
		array = tempArray;
	}

	@Override
	public String toString() {

		StringBuilder res = new StringBuilder();
		res.append(String.format("Queue: size = %d , capacity = %d\n", getSize(), this.array.length - 1));
		res.append("front [");
		for (int i = front; i != tail; i = (i + 1) % array.length) {
			res.append(array[i]);
			if ((i + 1) % array.length != tail)
				res.append(", ");
		}
		res.append("] tail");
		return res.toString();
	}


}
