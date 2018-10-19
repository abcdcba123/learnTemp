package Queue;

public class ArrayQueue<E> implements Queue<E> {

	private ObjectArray<E> array;

	public ArrayQueue(int capacity) {
		this.array = new ObjectArray<>(capacity);
	}

	public ArrayQueue() {
		this.array = new ObjectArray<>();
	}

	@Override
	public int getSize() {
		return this.array.getSize();
	}

	@Override
	public boolean isEmpty() {
		return this.array.isEmpty();
	}

	@Override
	public void push(E e) {
		this.array.addFirst(e);
	}

	@Override
	public E pop() {
		return this.array.removeLast();
	}

	@Override
	public E peek() {
		return this.array.getLast();
	}

	@Override
	public String toString() {

		StringBuilder res = new StringBuilder();
		res.append(String.format("ArrayQueue: size = %d , capacity = %d\n", getSize(), this.array.getCapacity()));
		res.append("[");
		for (int i = 0; i < array.getSize(); i++) {
			res.append(array.get(i));
			if (i != array.getSize() - 1)
				res.append(", ");
		}
		res.append("]");
		return res.toString();
	}
}
