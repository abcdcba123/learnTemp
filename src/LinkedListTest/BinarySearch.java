package LinkedListTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BinarySearch {

	public int binarySearch(int[] arr, int value, int begin, int end) {
		if (arr.length == 0 || end == 0) {
			return -1;
		}
		if (end == begin) {
			if (arr[begin] == value) {
				return begin;
			} else {
				return -1;
			}
		}
		if (end - begin == 1) {
			if (arr[begin] == value) {
				return begin;
			} else if (arr[end] == value) {
				return end;
			} else {
				return -1;
			}
		}
		int mid = (end - begin) / 2 + begin;
		if (arr[mid] == value) {
			return mid;
		} else if (arr[mid] > value) {
			// 查找mid左边的值
			return binarySearch(arr, value, begin, mid - 1);
		} else {
			// 查找mid右边的值
			return binarySearch(arr, value, mid + 1, end);
		}
	}

	public int binarySearchForLoop(int[] arr, int value) {
		int a = 0;
		int b = arr.length - 1;
		while (b >= a) {
			int mid = (b - a) / 2 + a;
			if (arr[mid] == value) {
				return mid;
			} else if (arr[mid] > value) {
				b = mid - 1;
			} else {
				a = mid + 1;
			}
		}
		return -1;
	}

	public int binarySearchForLoop2(int[] arr, int value) {
		int a = 0;
		int b = arr.length;
		while (b > a) {
			if (a == b) return -1;
			int mid = (b - a) / 2 + a;
			if (arr[mid] == value) {
				return mid;
			} else if (arr[mid] > value) {
				b = mid;
			} else {
				a = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = {1};
		BinarySearch binarySearchCreator = new BinarySearch();
		int a = binarySearchCreator.binarySearch(arr, 6, 0, arr.length - 1);
		System.out.println(a);
		int b = binarySearchCreator.binarySearchForLoop(arr, 1);
		System.out.println(b);

		int c = binarySearchCreator.binarySearchForLoop2(arr, 1);
		System.out.println(c);
	}
}
