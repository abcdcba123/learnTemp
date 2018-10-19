package Queue;

public class LinkedList<E> {

	private Node dummyHead;

	private int size;

	private class Node {
		public E e;
		public Node next;

		public Node(E e, Node node) {
			this.e = e;
			this.next = node;
		}

		public Node(E e) {
			this.e = e;
			this.next = null;
		}

		public Node() {
			this.e = null;
			this.next = null;
		}

		@Override
		public String toString() {
			return e.toString();
		}
	}


	public LinkedList(E e) {
		dummyHead = new Node(e);
	}

	public LinkedList() {
		dummyHead = new Node();
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// 链表新增节点
	public void addFirst(E e) {
//		Node newNode = new Node(e);
//		newNode.next = dummyHead;
//		dummyHead = newNode;

//		dummyHead = new Node(e, dummyHead);
//		size++;
		add(0, e);
	}

	// 在链表末尾添加新的元素e
	public void addLast(E e) {
		add(size, e);
	}

	public void add(int index, E e) {
		if (index < 0 || index > size)
			throw new IllegalArgumentException("Add failed. Illegal index.");
//		Node newNode = new Node(e);
//		Node prevNode = dummyHead;
//		for (int i = 0; i < index; i++){
//			prevNode = prevNode.next;
//		}
//		newNode.next = prevNode.next;
//		prevNode.next = newNode;
		Node prev = dummyHead;
		for (int i = 0; i < index; i++)
			prev = prev.next;

		prev.next = new Node(e, prev.next);

		size++;
	}

	public boolean find(E e) {
		Node tempNode = dummyHead;
		while (tempNode.next != null) {
			if (tempNode.e.equals(e)) {
				return true;
			}
			tempNode = tempNode.next;
		}
		return false;
	}

	public E get(int index) {
		Node prev = dummyHead;
		for (int i = 0; i < index; i++)
			prev = prev.next;
		return prev.next.e;
	}

	public void set(int index, E e) {
		Node prev = dummyHead;
		for (int i = 0; i < index; i++)
			prev = prev.next;
		prev.next.e = e;
	}

	public E remove(int index) {
		Node prev = dummyHead;
		for (int i = 0; i < index; i++)
			prev = prev.next;
		E result = prev.next.e;
		prev.next = prev.next.next;
		return result;
	}

	public E removeFirst() {
		return remove(0);
	}

	public E removeLast() {
		return remove(size);
	}

	public void removeElement(E e) {
		Node tempNode = dummyHead;
		while (tempNode.next != null) {
			if (tempNode.e.equals(e)) {
			}
			tempNode = tempNode.next;
		}
	}
}
