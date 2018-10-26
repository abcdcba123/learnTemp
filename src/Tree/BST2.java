package Tree;

import java.util.Random;

public class BST2<K extends Comparable<K>, V> {

	private class Node {
		public K key;
		public V value;
		public Node left, right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
	}

	private Node root;
	private int size;

	public BST2() {
		root = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	// 二分搜索树的前序遍历
	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Node node) {
		//递归终止条件
		if (node == null) return;

		System.out.println(node.key);
		preOrder(node.left);
		preOrder(node.right);
	}

	// 二分搜索树的中序遍历
	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(Node node) {

		if (node == null)
			return;

		inOrder(node.left);
		System.out.println(node.key);
		inOrder(node.right);
	}

	public void postOrder() {
		postOrder(root);
	}

	// 后序遍历以node为根的二分搜索树, 递归算法
	private void postOrder(Node node) {

		if (node == null)
			return;

		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.key);
	}

	// 向二分搜索树中添加新的元素e
	public void add(K key, V value) {
		root = add(root, key, value);
	}

	public void set(K key, V newValue) {
		Node node = getNode(root, key);
		if (node == null)
			throw new IllegalArgumentException(key + " doesn't exist!");

		node.value = newValue;
	}

	public V get(K key) {

		Node node = getNode(root, key);
		return node == null ? null : node.value;
	}

	// 返回以node为根节点的二分搜索树中，key所在的节点
	private Node getNode(Node node, K key) {

		if (node == null)
			return null;

		if (key.equals(node.key))
			return node;
		else if (key.compareTo(node.key) < 0)
			return getNode(node.left, key);
		else // if(key.compareTo(node.key) > 0)
			return getNode(node.right, key);
	}


	// 向以node为根的二分搜索树中插入元素e，递归算法
	// 返回插入新节点后二分搜索树的根
	private Node add(Node node, K key, V value) {

		if (node == null) {
			size++;
			return new Node(key, value);
		}

		if (key.compareTo(node.key) < 0)
			node.left = add(node.left, key, value);
		else if (key.compareTo(node.key) > 0)
			node.right = add(node.right, key, value);

		return node;
	}

	public boolean contains(K key) {
		return contains(root, key);
	}

	// 在以node为根的二分搜索树中搜索元素e，递归算法
	private boolean contains(Node node, K key) {
		if (node == null) {
			return false;
		}
		if (key.compareTo(node.key) == 0) {
			return true;
		} else if (key.compareTo(node.key) > 0) {
			return contains(node.right, key);
		} else {
			return contains(node.left, key);
		}
	}

	public Node findMinNode() {
		Node node = findMinNode(root);
		return node;
	}

	// 找到node节点下的最小值
	private Node findMinNode(Node node) {
		if (node.left == null) return node;
		return findMinNode(node.left);
	}

	// 删除掉以node为根的二分搜索树中的最小节点
	// 返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node) {

		if (node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			size--;
			return rightNode;
		}

		node.left = removeMin(node.left);
		return node;
	}

	//删除操作
	public void remove(K key) {

	}

	// 在以node为根的二分搜索树中删除key，递归算法
	private Node remove(Node node, K key) {
		if (node == null) {
			return node;
		}
		if (key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
			return node;
		} else if (key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
			return node;
		} else {
			// 待删除节点左子树为空的情况
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size--;
				return rightNode;
			}
			// 待删除节点右子树为空的情况
			if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				return leftNode;
			}
			// 待删除节点左右子树均不为空的情况
			// 获取右子树的下的最小元素替换为当前节点，用这个节点顶替待删除节点的位置

			Node minNode = findMinNode(node.right);
			minNode.right = removeMin(node.right);
			minNode.left = node.left;

			node.left = node.right = null;

			return minNode;
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		generateBSTString(root, 0, res);
		return res.toString();
	}

	// 生成以node为根节点，深度为depth的描述二叉树的字符串
	private void generateBSTString(Node node, int depth, StringBuilder res) {

		if (node == null) {
			res.append(generateDepthString(depth) + "null\n");
			return;
		}

		res.append(generateDepthString(depth) + node.key + "\n");
		generateBSTString(node.left, depth + 1, res);
		generateBSTString(node.right, depth + 1, res);
	}

	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < depth; i++)
			res.append("--");
		return res.toString();
	}


	public static void main(String[] args) {
		BST2<Integer, Integer> bst = new BST2<>();
		Random random = new Random();

		int n = 10000;

		for (int i = 0; i < n; i++)
			bst.add(random.nextInt(n), random.nextInt(n));
		bst.inOrder();
	}
}