package Set;


import Tree.AVLTree;
import Tree.FileOperation;

import java.util.ArrayList;

public class AVLTreeSet<E extends Comparable<E>> implements Set<E> {

	private AVLTree tree;

	public AVLTreeSet() {
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

	public static void main(String[] args) {
		System.out.println("Pride and Prejudice");
		ArrayList<String> words = new ArrayList<>();
		if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
			System.out.println("Total words: " + words.size());
			AVLTreeSet set1 = new AVLTreeSet();
			for (String word : words) {
				set1.add(word);
			}
			System.out.println("Total different words: " + set1.getSize());
		}
	}
}
