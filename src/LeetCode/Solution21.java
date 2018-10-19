package LeetCode;

public class Solution21 {

	public ListNode node1;
	public ListNode node2;

	private class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			StringBuilder res = new StringBuilder();
			res.append("Node: front ");

			ListNode cur = this;
			int i = 0;
			while (cur != null) {
				i++;
				if (i > 10) break;
				res.append(cur.val + "->");
				cur = cur.next;
			}
			return res.toString();
		}
	}

	public Solution21() {
		node1 = new ListNode(5);
//		node1.next = new ListNode(2);
//		node1.next.next = new ListNode(4);
		node2 = new ListNode(1);
		node2.next = new ListNode(3);
		node2.next.next = new ListNode(4);
//		node1 = new ListNode(1);
//		node2 = null;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			ListNode temp = l1;
			l1 = l2;
			l2 = temp;
		} else if (l2 != null && l1.val > l2.val) {
			ListNode temp = l1;
			l1 = l2;
			l2 = temp;
		}
		ListNode head = l1;
		ListNode forWhile = l1;
		while (forWhile != null) {
			ListNode tempNode = forWhile.next;
			if (forWhile.next == null) {
				forWhile.next = l2;
				break;
			} else if (l2 != null && l2.val >= forWhile.val && l2.val < forWhile.next.val) {
				// 摘取l2_start - l2_end
				ListNode temp1 = l2;
				while (l2.next != null && l2.next.val < forWhile.next.val) {
					l2 = l2.next;
				}
				forWhile.next = temp1;
				ListNode temp2 = l2.next;
				l2.next = tempNode;
				l2 = temp2;
			}
			forWhile = tempNode;
		}
		return head;
	}

	public static void main(String[] args) {
		Solution21 test = new Solution21();
		System.out.println(test.mergeTwoLists(test.node1, test.node2));
	}

}
