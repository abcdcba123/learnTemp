package Tree;

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
		if (Math.abs(balanceFactor) > 1)
			System.out.println("unbalanced : " + balanceFactor);

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
		if (balanceFactor < -1 && getBalanceFactor(node.right) >= 0)
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
			node.left = leftRotate(node);
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
		if (balanceFactor < -1 && getBalanceFactor(node.right) < 0) {
			node.right = rightRotate(node);
			return leftRotate(node);
		}


		return node;
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

		//左旋转
		x.left = y;
		y.right = T2;

		// 计算高度
		y.height = Math.max(getHeight(y.right), getHeight(y.left)) + 1;
		x.height = Math.max(getHeight(x.right), getHeight(x.left)) + 1;

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
}
