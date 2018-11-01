package Map;

import Tree.AVLTree;
import Tree.FileOperation;

import java.util.ArrayList;

public class AVLTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

	private AVLTree<K, V> avlTree;

	public AVLTreeMap() {
		avlTree = new AVLTree<>();
	}

	@Override
	public boolean isEmpty() {
		return avlTree.isEmpty();
	}

	@Override
	public int getSize() {
		return avlTree.getSize();
	}

	@Override
	public void add(K key, V value) {
		avlTree.add(key, value);
	}

	@Override
	public void remove(K key) {
		avlTree.remove(key);
	}

	@Override
	public boolean contains(K key) {
		return avlTree.contains(key);
	}

	@Override
	public V get(K key) {
		return avlTree.get(key);
	}

	@Override
	public void set(K key, V value) {
		avlTree.set(key, value);
	}

	public static void main(String[] args) {
		System.out.println("Pride and Prejudice");
		ArrayList<String> words = new ArrayList<>();
		if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
			System.out.println("Total words: " + words.size());
			AVLTreeMap<String, Integer> map1 = new AVLTreeMap();
			for (String word : words) {
				if (map1.contains(word)) {
					map1.set(word, map1.get(word) + 1);
				} else {
					map1.add(word, 1);
				}
			}
			System.out.println("Total different words: " + map1.getSize());
			System.out.println("Frequency of PRIDE: " + map1.get("pride"));
			System.out.println("Frequency of PREJUDICE: " + map1.get("prejudice"));
		}
	}

}
