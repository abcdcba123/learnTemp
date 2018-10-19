package Queue;

public class LoopQueue<E> implements Queue<E> {

	private E[] array;
	private int front, tail;
	private int size;

	public LoopQueue(int capacity) {
		array = (E[]) new Object[capacity + 1];
		front = 0;
		tail = 0;
		size = 0;
	}

	public LoopQueue() {
		this(10);
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public void push(E e) {
		// 扩容
//		if (size == array.length - 1) {
//			resize(array.length * 2);
//		}
		if ((tail + 1) % array.length == front) {
			resize(array.length * 2);
		}
		array[size] = e;
		size++;
		tail = (tail + 1) % this.array.length;
//		if (tail == this.array.length) {
//			tail = 0;
//		} else {
//			tail++;
//		}
	}

	@Override
	public E pop() {
		if (isEmpty())
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

		E result = array[front];
		array[front] = null;
		front = (front + 1) % array.length;
//		if (front == array.length) {
//			front = 0;
//		} else {
//			front++;
//		}
		size--;

		// 缩容
		if (size <= array.length / 4 && array.length / 2 != 0) {
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
		for (int i = 0; i < size; i++)
			tempArray[i] = array[(i + front) % array.length];
//		for (int i = front; i < tail; i = (i + front) % size) {
//			tempArray[start] = array[i];
//			start++;
//		}
		array = tempArray;
		front = 0;
		tail = size;
//		tail = size - 1;
	}

	@Override
	public String toString() {

		StringBuilder res = new StringBuilder();
		res.append(String.format("Queue: size = %d , capacity = %d\n", size, this.array.length - 1));
		res.append("front [");
		for (int i = front; i != tail; i = (i + 1) % array.length) {
			res.append(array[i]);
			if ((i + 1) % array.length != tail)
				res.append(", ");
		}
		res.append("] tail");
		return res.toString();
	}

	public static void main(String[] args) {

		LoopQueue<Integer> queue = new LoopQueue<>();
		for (int i = 0; i < 10; i++) {
			queue.push(i);
			System.out.println(queue);

			if (i % 3 == 2) {
				queue.pop();
				System.out.println(queue);
			}
		}
	}


}
