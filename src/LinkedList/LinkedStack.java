package LinkedList;

public class LinkedStack<E> implements Stack<E> {

	private LinkedList<E> linkedList;

	public LinkedStack() {
		LinkedList linkedList = new LinkedList();
	}

	@Override
	public int getSize() {
		return linkedList.getSize();
	}

	@Override
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	@Override
	public void push(E e) {
		linkedList.addFirst(e);
	}

	@Override
	public E pop() {
		return linkedList.removeFirst();
	}

	@Override
	public E peek() {
		return linkedList.get(0);
	}
}
