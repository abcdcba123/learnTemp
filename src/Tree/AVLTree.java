package Tree;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

	private class Node {
		public K key;
		public V value;
		public Node right, left;
		public int height;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.right = null;
			this.left = null;
			height = 1;
		}
	}

	private Node root;
	private int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(K key, V value) {
		root = add(root, key, value);
	}

	// 把k和v添加到以node为根的节点，返回新的根
	private Node add(Node node, K key, V value) {
		// 终止条件
		if (node == null) {
			size++;
			return new Node(key, value);
		}
		if (node.key.compareTo(key) < 0) {
			// key大于node的key，往右子树添加
			node.right = add(node.right, key, value);
		} else if (node.key.compareTo(key) > 0) {
			// key小于node的key，往左子树添加
			node.left = add(node.left, key, value);
		} else {
			// key.compareTo(node.key) == 0
			node.value = value;
		}
		// 更新height
		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

		// 计算平衡因子
		int balanceFactor = getBalanceFactor(node);
//		if (Math.abs(balanceFactor) > 1)
//			System.out.println("unbalanced : " + balanceFactor);

		// 平衡维护
		// LL 左倾斜 新插入的节点在node的左孩子的左侧
		// 对节点y进行向右旋转操作，返回旋转后新的根节点x
		//        y                              x
		//       / \                           /   \
		//      x   T4     向右旋转 (y)        z     y
		//     / \       - - - - - - - ->    / \   / \
		//    z   T3                       T1  T2 T3 T4
		//   / \
		// T1   T2
		if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
			return rightRotate(node);

		// RR 右倾斜 新插入的节点在node的右孩子的右侧
		// 对节点y进行向左旋转操作，返回旋转后新的根节点x
		//        y                              x
		//       / \                           /   \
		//      T1  x     向右旋转 (y)        y     z
		//        /  \   - - - - - - - ->    / \   / \
		//       T2   z                    T1  T2 T3 T4
		//           / \
		//          T3  T4
		if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
			return leftRotate(node);

		// LR  新插入的节点在node的左孩子的右侧
		// 对节点y进行向左旋转操作，返回旋转后新的根节点x
		//        y                              y
		//       / \                           /   \
		//      x   T4   向左旋转 (x)         z     T4       向右旋转 (y)
		//     /  \   - - - - - - - ->      / \           - - - - - - - ->
		//    T1   z                       x  T3
		//        / \                     / \
		//       T2  T3                  T1  T2
		if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// RL 新插入的节点在node的右孩子的左侧
		// 对节点y进行向左旋转操作，返回旋转后新的根节点x
		//        y                              y
		//       / \                           /   \
		//      T1   x   向右旋转 (x)          T1   z           向左旋转 (y)
		//         /  \   - - - - - - - ->         / \       - - - - - - - ->
		//        z   T4                          T2  x
		//       / \                                 / \
		//      T2  T3                             T3  T4
		if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	public V remove(K key) {
		Node node = getNode(root, key);
		if (node != null) {
			root = remove(root, key);
			return node.value;
		}
		return null;
	}

	// 删除以node为根的二
	private Node remove(Node node, K key) {
		//递归终止条件，节点为空或者要删除的就是此节点
		if (node == null) return null;

		Node retNode;
		if (node.key.compareTo(key) > 0) {
			//要删除的节点在node的左子树
			retNode = remove(node.left, key);
		} else if (node.key.compareTo(key) > 0) {
			//要删除的节点在node的右子树
			retNode = remove(node.right, key);
		} else {
			//要删除的就是此节点
			// 待删除节点左子树为空的情况
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size--;
				// return rightNode;
				retNode = rightNode;
			}
			// 待删除节点右子树为空的情况
			else if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				// return leftNode;
				retNode = leftNode;
			}

			// 待删除节点左右子树均不为空的情况
			else {
				// 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
				// 用这个节点顶替待删除节点的位置
				Node successor = findMin(node.right);
				//successor.right = removeMin(node.right);
				successor.right = remove(node.right, successor.key);
				successor.left = node.left;

				node.left = node.right = null;

				// return successor;
				retNode = successor;
			}
		}
		if (retNode == null)
			return null;

		// 更新height
		retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

		// 计算平衡因子
		int balanceFactor = getBalanceFactor(retNode);

		// 平衡维护
		// LL
		if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
			return rightRotate(retNode);

		// RR
		if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
			return leftRotate(retNode);

		// LR
		if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
			retNode.left = leftRotate(retNode.left);
			return rightRotate(retNode);
		}

		// RL
		if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
			retNode.right = rightRotate(retNode.right);
			return leftRotate(retNode);
		}
		return retNode;
	}

	// 寻找以node为根的最小值
	private Node findMin(Node node) {
		//递归结束条件
		if (node == null) return node;
		if (node.left == null) {
			//此node就是最小值
			return node;
		}
		return findMin(node.left);
	}

	// 对节点y进行向右旋转操作，返回旋转后新的根节点x
	//        y                              x
	//       / \                           /   \
	//      x   T4     向右旋转 (y)        z     y
	//     / \       - - - - - - - ->    / \   / \
	//    z   T3                       T1  T2 T3 T4
	//   / \
	// T1   T2
	private Node rightRotate(Node y) {
		Node x = y.left;
		Node T3 = x.right;

		// 向右旋转过程
		x.right = y;
		y.left = T3;

		//以上四行代码可变为：
		// Node x = y.left;
		// y.left = x.right;
		// x.right = y;

		// 更新height
		y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
		x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

		return x;
	}

	// 对节点y进行向左旋转操作，返回旋转后新的根节点x
	//        y                              x
	//       / \                           /   \
	//      T1  x     向右旋转 (y)        y     z
	//        /  \   - - - - - - - ->    / \   / \
	//       T2   z                    T1  T2 T3 T4
	//           / \
	//          T3  T4
	private Node leftRotate(Node y) {
		Node x = y.right;
		Node T2 = x.left;

		// 向左旋转过程
		x.left = y;
		y.right = T2;

		// 更新height
		y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
		x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

		return x;
	}

	// 获得节点node的高度
	private int getHeight(Node node) {
		if (node == null)
			return 0;
		return node.height;
	}

	private int getBalanceFactor(Node node) {
		if (node == null)
			return 0;
		return getHeight(node.left) - getHeight(node.right);
	}

	public boolean contains(K key) {
		return getNode(root, key) != null;
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

	public void set(K key, V newValue) {
		Node node = getNode(root, key);
		if (node == null)
			throw new IllegalArgumentException(key + " doesn't exist!");

		node.value = newValue;
	}

	// 判断该二叉树是否是一棵二分搜索树
	public boolean isBST() {

		ArrayList<K> keys = new ArrayList<>();
		inOrder(root, keys);
		for (int i = 1; i < keys.size(); i++)
			if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
				return false;
		return true;
	}

	private void inOrder(Node node, ArrayList<K> keys) {

		if (node == null)
			return;

		inOrder(node.left, keys);
		keys.add(node.key);
		inOrder(node.right, keys);
	}

	// 判断该二叉树是否是一棵平衡二叉树
	public boolean isBalanced() {
		return isBalanced(root);
	}

	// 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
	private boolean isBalanced(Node node) {

		if (node == null)
			return true;

		int balanceFactor = getBalanceFactor(node);
		if (Math.abs(balanceFactor) > 1)
			return false;
		return isBalanced(node.left) && isBalanced(node.right);
	}

	public static void main(String[] args) {

		System.out.println("Pride and Prejudice");

		ArrayList<String> words = new ArrayList<>();
		if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
			System.out.println("Total words: " + words.size());

			AVLTree<String, Integer> map = new AVLTree<>();
			for (String word : words) {
				if (map.contains(word))
					map.set(word, map.get(word) + 1);
				else
					map.add(word, 1);
			}

			System.out.println("Total different words: " + map.getSize());
			System.out.println("Frequency of PRIDE: " + map.get("pride"));
			System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

			System.out.println("is BST : " + map.isBST());
			System.out.println("is Balanced : " + map.isBalanced());
		}

		System.out.println();
	}
}
