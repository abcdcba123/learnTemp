package LeetCode;

public class Solution206 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode reverseList(ListNode head) {
		ListNode dummyHead = new ListNode(-1);
		while (head != null) {
			ListNode node = new ListNode(head.val);
			node.next = dummyHead.next;
			dummyHead.next = node;
			head = head.next;
		}
		return dummyHead.next;
	}
}
