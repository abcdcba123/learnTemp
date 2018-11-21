package LinkedListTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedListCreator {

	/**
	 * 严格定义递归函数作用，包括参数，返回值，Side-effect
	 * 先一般后特殊
	 * 每次调用必须缩小问题的规模
	 * 问题的规模每次必须只缩小1
	 * Create a linked list.
	 *
	 * @param data the data to create the list.
	 * @return head of the linked list. The returned linked list ends with last node with getNext() = null.
	 */
	public Node createLinkedListWithStack(List<Integer> data) {
		if (data.isEmpty()) {
			return null;
		}
		//先一般后特殊
		Node firstNode = new Node(data.get(0));
		// 每次调用必须缩小问题的规模
		// 问题的规模每次必须只缩小1
		Node headOfSubList = createLinkedListWithStack(data.subList(1, data.size()));//假设headOfSubList是SubList的头部
		firstNode.setNext(headOfSubList);
		return firstNode;
	}


	public Node createLinkedListWithLoop(List<Integer> data) {
		if (data.isEmpty()) return null;
		int i = data.size() - 1;
		Node head = null;
		Node prevNode = null;
		while (i >= 0) {
			head = new Node(data.get(i));
			head.setNext(prevNode);
			prevNode = head;
			i--;
		}
		return head;
	}

	public Node createLargeNumberLinkedListWithLoop(int n) {
		Node prev = null;
		Node head = null;
		for (int i = 1; i <= n; i++) {
			Node node = new Node(i);
			if (prev != null) {
				prev.setNext(node);
			} else {
				head = node;
			}
			prev = node;
		}
		return head;
	}

	/**
	 * 反转链表
	 * 1->2->3->4->null   =>  4->3->2->1->null
	 * 先一般 后特殊
	 * 1->   4->3->2->null
	 * 1->null  2->1
	 *
	 * @param head
	 * @return
	 */
	public Node reversalLinkedListWithStack(Node head) {
//		if (head == null){
//			return null;
//		}
//		if (head.getNext() == null){
//			return head;
//		}
		if (head == null || head.getNext() == null) {
			return head;
		}
		Node newHead = reversalLinkedListWithStack(head.getNext()); // 假设这是已经反转完成的头结点
		Node nextNode = head.getNext();
		head.setNext(null);
		nextNode.setNext(head);
		return newHead;
	}

	public Node reversalLinkedListWithLoop(Node head) {
		Node prevHead = null;
		Node curHead = head;
		while (curHead != null) {
			Node CurNextHead = curHead.getNext();
			curHead.setNext(prevHead);
			prevHead = curHead;
			curHead = CurNextHead;
		}
		return prevHead;
	}

	public static void main(String[] args) {
		// 创建链表 Stack
		LinkedListCreator creator = new LinkedListCreator();
//		Node head1 = creator.createLinkedListWithStack(new ArrayList<>());
//		Node head2 = creator.createLinkedListWithStack(Arrays.asList(1));
//		Node head3 = creator.createLinkedListWithStack(Arrays.asList(1, 2, 3, 4, 5, 6));
//		Node.printLinkedList(head1);
//		Node.printLinkedList(head2);
//		Node.printLinkedList(head3);

		// 反转链表 Stack
//		Node reversalNode = creator.reversalLinkedListWithStack(head3);
//		Node.printLinkedList(reversalNode);

		// 创建链表 loop
//		Node loopHead1 = creator.createLinkedListWithLoop(Arrays.asList(1, 2, 3, 4, 5, 6));
//		Node.printLinkedList(loopHead1);

		// 创建长链表 loop
		Node loopHead2 = creator.createLargeNumberLinkedListWithLoop(1000000);
//		Node.printLinkedList(loopHead2);

		Node reversalLoopNode = creator.reversalLinkedListWithLoop(loopHead2);
//		Node.printLinkedList(reversalLoopNode);
		System.out.println("complete");
	}
}
