package LeetCode;

//请判断一个链表是否为回文链表。
//
//示例 1:
//
//输入: 1->2
//输出: false
//示例 2:
//
//输入: 1->2->2->1
//输出: true
//进阶：
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Solution234 {
	public ListNode node1;

	private class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			StringBuilder res = new StringBuilder();
			res.append("ListNode: front ");

			ListNode cur = this;
			while (cur != null) {
				res.append(cur.val);
				res.append("->");
				cur = cur.next;
			}
			res.append("NULL tail");
			return res.toString();
		}
	}

	public Solution234() {
		node1 = new ListNode(1);
//		node1.next = new ListNode(2);
//		node1.next.next = new ListNode(3);
//		node1.next.next.next = new ListNode(2);
//		node1.next.next.next.next = new ListNode(1);
	}

	public boolean isPalindrome(ListNode head) {
		if (head == null) return true;
		boolean mid = true;
		ListNode compare = head;
		ListNode middle = head;
		//获取到中间节点
		while (head.next != null) {
			head = head.next;
			if (mid) {
				middle = middle.next;
			}
			mid = !mid;
		}
		//反转中间节点之前的节点
		ListNode dummyHead = new ListNode(-1);
		while (middle != null) {
			ListNode temp = dummyHead.next;
			dummyHead.next = new ListNode(middle.val);
			dummyHead.next.next = temp;
			middle = middle.next;
		}
		middle = dummyHead.next;
//		System.out.println(compare);
//		System.out.println(middle);
		//比较后一半的节点和前一半的节点
		while (middle != null) {
			if (middle.val != compare.val) {
				return false;
			}
			compare = compare.next;
			middle = middle.next;
		}
		return true;
	}

	public static void main(String[] args) {
		Solution234 test = new Solution234();
		System.out.println(test.isPalindrome(test.node1));
	}
}
