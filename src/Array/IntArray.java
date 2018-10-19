package Array;

public class IntArray {
	private int[] data;
	private int capacity;
	private int size;

	// 构造函数，传入数组的容量capacity构造Array
	public IntArray(int capacity) {
		this.capacity = capacity;
		this.data = new int[capacity];
		this.size = 0;
	}

	// 无参数构造函数，默认capacity = 10;
	public IntArray() {
		this(10);
	}

	// 获取元素个数
	public int getSize() {
		return this.size;
	}

	// 获取数组容量
	public int getCapacity() {
		return this.capacity;
	}

	// 返回是否为空
	public boolean isEmpty() {
		return this.size == 0;
	}

	// 在index索引的位置插入一个新int
	public void add(int index, int data) {
		if (this.size + 1 >= this.capacity) {
			//扩容操作
			this.resize(this.capacity * 2);
		}
		for (int i = this.size - 1; i >= index; i--) {
			this.data[i + 1] = this.data[i];
		}
		this.data[index] = data;
		this.size++;
	}

	// 根据索引删除元素
	public int remove(int index) {
		if (index < 0 || index > this.size)
			throw new IllegalArgumentException("index is illegal");
		int result = this.data[index];
		for (int i = index; i < this.size; i++) {
			this.data[i] = this.data[i + 1];
		}
		this.size--;
		if (this.size < this.capacity / 4) {
			//扩容操作
			this.resize(this.capacity / 2);
		}
		return result;
	}

	// 通过索引获取元素
	public int get(int index) {
		if (index < 0 || index > this.size)
			throw new IllegalArgumentException("index is illegal");
		return this.data[index];
	}

	// 通过值搜索元素是否存在
	public boolean contains(int data) {
		for (int i = 0; i < this.size; i++) {
			if (this.data[i] == data) {
				return true;
			}
		}
		return false;
	}

	// 通过值搜索元素的键值，如果没有返回-1
	public int find(int data) {
		for (int i = 0; i < this.size; i++) {
			if (this.data[i] == data) {
				return i;
			}
		}
		return -1;
	}

	// 删除第一个元素
	public int removeFirst() {
		return this.remove(0);
	}

	// 删除最后一个元素
	public int removeLast() {
		return this.remove(this.size - 1);
	}

	// 根据值来删除元素
	public void removeElement(int data) {
		int index = this.find(data);
		if (index != -1)
			this.remove(index);
	}


	// 扩容或者缩容
	private void resize(int capacity) {
		this.capacity = capacity;
		int[] tempData = new int[capacity];
		for (int i = 0; i < this.size; i++) {
			tempData[i] = this.data[i];
		}
		this.data = tempData;
	}
}
