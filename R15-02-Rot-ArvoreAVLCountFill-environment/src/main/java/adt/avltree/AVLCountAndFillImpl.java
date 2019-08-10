package adt.avltree;
import java.util.Arrays;

import adt.bst.BSTNode;
import adt.bt.Util;

import java.util.ArrayList;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@SuppressWarnings("unchecked")
	public void fillWithoutRebalance(T[] array) {

		ArrayList<T> aux = new ArrayList<>();
		T[] a = this.postOrder();
		
		for (T t : a) {
			aux.add(t);
		}
		
		this.root = new BSTNode<T>();
		
		for (T t : array) {
			aux.add(t);
		}
		
		array = (T[]) aux.toArray(new Comparable[aux.size()]);
		Arrays.sort(array);
		T[] arrayAux = (T[]) new Comparable[array.length * 2];
		fillWithoutRebalace(array, arrayAux, 0, array.length - 1, 0);
		
		for (T t : arrayAux) {
			if (t != null) {
				this.insert(t);
			}
		}
		
	}

	public void fillWithoutRebalace(T[] array, T[] arrayAux, int comeco, int fim, int indice) {
		if (comeco <= fim) {
			int index = (int) ((comeco + fim) / 2);
			T aux = array[index];
			arrayAux[indice] = aux;
			fillWithoutRebalace(array, arrayAux, comeco, index - 1, (2 * indice) + 1);
			fillWithoutRebalace(array, arrayAux, index + 1, fim, (2 * indice) + 2);
		}
	}
	
	// AUXILIARY
		protected void rebalance(BSTNode<T> node) {
			int height = calculateBalance(node);
			if (height > 1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
					Util.rightRotation((BSTNode<T>) node.getRight());
					Util.leftRotation(node);
					this.RLcounter++;
				} else {
					Util.leftRotation(node);
					this.RRcounter++;
				}
			} else if (height < -1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
					Util.leftRotation((BSTNode<T>) node.getLeft());
					Util.rightRotation(node);
					this.LRcounter++;
				} else {
					Util.rightRotation(node);
					this.LLcounter++;
				}
			}
		}
}