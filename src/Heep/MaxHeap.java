package Heep;

import Array.ObjectArray;

import java.util.List;
import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {

	private ObjectArray<E> data;

	public MaxHeap(int capacity) {
		data = new ObjectArray<>(capacity);
	}

	public MaxHeap() {
		data = new ObjectArray<>();
	}

	public MaxHeap(E[] arr) {
		data = new ObjectArray<>(arr);
		//使用Heapify
		for (int i = parent(arr.length - 1); i >= 0; i--)
			siftDown(i);
	}

	public int getSize() {
		return data.getSize();
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public void add(E e) {
		data.addLast(e);
//		data.swap(0,data.getSize() - 1);
		siftUp(data.getSize() - 1);
	}

	// 看堆中的最大元素
	public E findMax() {
		if (data.getSize() == 0)
			throw new IllegalArgumentException("Can not findMax when heap is empty.");
		return data.get(0);
	}

	// 取出堆中最大元素
	public E extractMax() {
		if (data.getSize() == 0)
			throw new IllegalArgumentException("Can not findMax when heap is empty.");
		E result = data.get(0);
		data.swap(0, data.getSize() - 1);
		data.removeLast();
		siftDown(0);
		return result;
	}

	// 取出堆中的最大值，并且替换成元素e
	public E replace(E e) {
		E ret = findMax();
		data.set(0, e);
		siftDown(0);
		return ret;
	}


	// 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
	private int parent(int index) {
		if (index == 0)
			throw new IllegalArgumentException("index-0 doesn't have parent.");
		return (index - 1) / 2;
	}

	private int leftChild(int index) {
		return index * 2 + 1;
	}

	private void siftDown(int index) {
		while (leftChild(index) < data.getSize()) {
			int j = leftChild(index);
			if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
				// 如果右子树比较大，则比较index位置和右子树位置
				j++;
			}
			if (data.get(index).compareTo(data.get(j)) > 0) {
				//满足index位置，在index leftChild rightChild中index最大
				break;
			}
			data.swap(index, j);
			index = j;
		}
	}

	private void siftUp(int k) {

		while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
			data.swap(k, parent(k));
			k = parent(k);
		}
	}

	@Override
	public String toString() {
		return data.toString();
	}

	private static void printList(List<Integer> nums) {
		for (Integer num : nums)
			System.out.print(num + " ");
		System.out.println();
	}

	public double testHeap(E[] data, boolean isHeapify) {
		long startTime = System.nanoTime();
		MaxHeap<E> maxHeap;
		if (isHeapify) {
			maxHeap = new MaxHeap<>(data);
		} else {
			maxHeap = new MaxHeap<>();
			for (E num : data) {
				maxHeap.add(num);
			}
		}
		long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}

	public static double testHeap(Integer[] data, boolean isHeapify) {
		long startTime = System.nanoTime();
		MaxHeap<Integer> maxHeap;
		if (isHeapify) {
			maxHeap = new MaxHeap<>(data);
		} else {
			maxHeap = new MaxHeap<>();
			for (int num : data) {
				maxHeap.add(num);
			}
		}
		long endTime = System.nanoTime();
		return (endTime - startTime) / 1000000000.0;
	}

	public static void main(String[] args) {
		int[] nums = {1, 1, 5, 2, 2, 3};
		MaxHeap heap = new MaxHeap();
		for (int i = 0; i < nums.length; i++) {
			heap.add(nums[i]);
		}
		System.out.println(heap);

		int n = 10000000;
		Random random = new Random();
		Integer[] testData = new Integer[n];
		for (int i = 0; i < n; i++) {
			testData[i] = random.nextInt(Integer.MAX_VALUE);
		}

		double time1 = testHeap(testData, false);
		System.out.println("Without Heapify time1:" + time1);

		double time2 = testHeap(testData, true);
		System.out.println("With Heapify time2:" + time2);
	}
}
