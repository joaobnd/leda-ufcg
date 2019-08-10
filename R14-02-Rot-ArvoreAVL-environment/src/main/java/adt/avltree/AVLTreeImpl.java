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
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, getRoot());
		}
	}

	@Override
	protected void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().parent(node).build());
			node.setRight(new BSTNode.Builder<T>().parent(node).build());
		} else {
			if (element.compareTo(node.getData()) < 0) {
				this.insert(element, (BSTNode<T>) node.getLeft());
			} else {
				this.insert(element, (BSTNode<T>) node.getRight());
			}
			rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		remove(search(element));
	}

	@Override
	protected BSTNode<T> remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
				rebalanceUp(node);
			} else if (oneChild(node)) {
				if (node != root) {
					if (node.getParent().getLeft() == node) {
						if (node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						}
					} else {
						if (node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						}
					}
				} else {
					if (root.getLeft().isEmpty())
						root = (BSTNode<T>) root.getRight();
					else {
						root = (BSTNode<T>) root.getLeft();
					}
				}
				rebalance(node);
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				remove(sucessor);
			}
		}
		return node;
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			return this.height((BSTNode<T>) node.getRight()) - this.height((BSTNode<T>) node.getLeft());
		}
		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int height = calculateBalance(node);
		if (height > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				rightRotation((BSTNode<T>) node.getRight());
				leftRotation((BSTNode<T>) node);
			} else {
				leftRotation(node);
			}
		} else if (height < -1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
				rightRotation(node);
			} else {
				leftRotation((BSTNode<T>) node.getLeft());
				rightRotation(node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while (parent != null) {
				this.rebalance(parent);
				parent = (BSTNode<T>) parent.getParent();
			}
		}
	}

	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();

		if (this.getRoot() == node) {
			this.root = pivot;
		}
		Util.leftRotation(node);
	}

	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();

		if (this.getRoot() == node) {
			this.root = pivot;
		}
		Util.rightRotation(node);
	}

}