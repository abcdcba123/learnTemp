package Set;


import Tree.AVLTree;

public class TreeSet<E extends Comparable<E>> implements Set<E> {

	private AVLTree tree;

	public TreeSet() {
		tree = new AVLTree();
	}

	@Override
	public int getSize() {
		return tree.getSize();
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	public void add(E e) {
		tree.add(e, e);
	}

	@Override
	public void remove(E e) {
		tree.remove(e);
	}

	@Override
	public boolean contains(E e) {
		return tree.contains(e);
	}
}
