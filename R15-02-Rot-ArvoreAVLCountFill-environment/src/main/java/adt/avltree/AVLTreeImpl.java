package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			return height((BSTNode<T>) node.getRight()) - height((BSTNode<T>) node.getLeft()); 
		}
		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int height = calculateBalance(node);
		if (height > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				Util.rightRotation((BSTNode<T>) node.getRight());
				Util.leftRotation(node);
			} else {
				Util.leftRotation(node);
			}
		} else if (height < -1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
				Util.leftRotation((BSTNode<T>) node.getLeft());
				Util.rightRotation(node);
			} else
				Util.rightRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			if (parent != null) {
				rebalance(parent);
				rebalanceUp((BSTNode<T>) parent);
			}
		}
	}
	
	@Override
	public void remove(T element) {
		
		BSTNode<T> node = search(element);
		BSTNode<T> toRebalance;
		
		if (!node.isEmpty() && element != null) {
			
			if (!node.isLeaf()) {
				toRebalance = (BSTNode<T>) super.sucessor(element);				
			} else {
				toRebalance = (BSTNode<T>) node;
			}
			
			super.remove(node);
			rebalanceUp((BSTNode<T>) toRebalance);
			
		}
		
	}
	
	public void insert(T element) {
		if (element != null) {
			BSTNode<T> node = super.insert(element, getRoot());
			rebalanceUp(node);
		}
	}
}