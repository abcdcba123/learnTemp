package Stack;

public class ArrayStack<E> implements Stack<E> {

	private ObjectArray<E> array;

	public ArrayStack(int capacity) {
		this.array = new ObjectArray<>(capacity);
	}

	public ArrayStack() {
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
		this.array.addLast(e);
	}

	@Override
	public E pop() {
		return this.array.removeLast();
	}

	@Override
	public E peek() {
		return this.array.getLast();
	}
}
