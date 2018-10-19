package Queue;

public class ObjectArray<E> {

	private E[] data;
	private int size;

	// 构造函数，传入数组的容量capacity构造Array
	public ObjectArray(int capacity) {
		this.data = (E[]) new Object[capacity];
		this.size = 0;
	}

	// 无参数构造函数，默认capacity = 10;
	public ObjectArray() {
		this(10);
	}

	// 获取元素个数
	public int getSize() {
		return this.size;
	}

	// 获取数组容量
	public int getCapacity() {
		return this.data.length;
	}

	// 返回是否为空
	public boolean isEmpty() {
		return this.size == 0;
	}

	// 在index索引的位置插入一个新int
	public void add(int index, E e) {
		if (this.size == this.data.length) {
			//扩容操作
			this.resize(this.data.length * 2);
		}
		for (int i = this.size - 1; i >= index; i--) {
			this.data[i + 1] = this.data[i];
		}
		this.data[index] = e;
		this.size++;
	}

	// 在数组末尾添加元素e
	public void addLast(E e) {
		this.add(this.size, e);
	}

	// 在数组开头添加元素e
	public void addFirst(E e) {
		this.add(0, e);
	}

	// 根据索引删除元素
	public E remove(int index) {
		if (index < 0 || index > this.size)
			throw new IllegalArgumentException("index is illegal");
		E result = this.data[index];
		for (int i = index + 1; i < this.size; i++) this.data[i - 1] = this.data[i];
		this.size--;
		if (this.size < this.data.length / 4 && this.data.length / 2 > 0) {
			//缩容操作
			this.resize(this.data.length / 2);
		}
		return result;
	}

	// 通过索引获取元素
	public E get(int index) {
		if (index < 0 || index > this.size)
			throw new IllegalArgumentException("index is illegal");
		return this.data[index];
	}

	public E getFirst() {
		return this.get(0);
	}

	public E getLast() {
		return this.get(this.size);
	}


	// 通过值搜索元素是否存在
	public boolean contains(E data) {
		for (int i = 0; i < this.size; i++) {
			if (this.data[i].equals(data)) {
				return true;
			}
		}
		return false;
	}

	// 通过值搜索元素的键值，如果没有返回-1
	public int find(E data) {
		for (int i = 0; i < this.size; i++) {
			if (this.data[i].equals(data)) {
				return i;
			}
		}
		return -1;
	}

	// 删除第一个元素
	public E removeFirst() {
		return this.remove(0);
	}

	// 删除最后一个元素
	public E removeLast() {
		return this.remove(this.size - 1);
	}

	// 根据值来删除元素
	public void removeElement(E data) {
		int index = this.find(data);
		if (index != -1)
			this.remove(index);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("ObjectArray:");
		result.append("[");
		for (int i = 0; i < this.size; i++) {
			result.append(this.data[i]);
			if (i != this.size - 1) {
				result.append(",");
			}
		}
		result.append("] top");
		return result.toString();
	}

	// 扩容或者缩容
	private void resize(int capacity) {
		E[] tempData = (E[]) new Object[capacity];
		for (int i = 0; i < this.size; i++) {
			tempData[i] = this.data[i];
		}
		this.data = tempData;
	}
}
