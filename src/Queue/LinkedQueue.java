package Queue;

public class LinkedQueue<E> implements Queue<E> {

	private Node head, tail;
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

	public LinkedQueue() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void push(E e) {
		if (size == 0) {
			head = new Node(e);
			tail = head;
		} else {
			tail.next = new Node(e);
			tail = tail.next;
		}
		size++;
	}

	@Override
	public E pop() {
		E result = head.e;
		head = head.next;
		size--;
		return result;
	}

	@Override
	public E peek() {
		return head.e;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Queue: front ");

		Node cur = head;
		while (cur != null) {
			res.append(cur + "->");
			cur = cur.next;
		}
		res.append("NULL tail");
		return res.toString();
	}

	public static void main(String[] args) {

		LinkedQueue<Integer> queue = new LinkedQueue<>();
		for (int i = 0; i < 11; i++) {
			queue.push(i);
			System.out.println(queue);

			if (i % 3 == 2) {
				queue.pop();
				System.out.println(queue);
			}
		}
	}
}
