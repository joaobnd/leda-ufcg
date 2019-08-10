package adt.rbtree;

import java.util.ArrayList;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	 protected int blackHeight() {
	      int size = 0;
	      if (!this.root.isEmpty()) {
	         RBNode<T> node = (RBNode<T>) this.root;
	         while (!node.isEmpty()) {
	            if (node.getColour() == Colour.BLACK) {
	               size += 1;
	            }
	            node = (RBNode<T>) node.getRight();
	         }
	      }
	      return size;

	   }

	   protected boolean verifyProperties() {
	      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
	            && verifyBlackHeight();

	      return resp;
	   }

	   /**
	    * The colour of each node of a RB tree is black or red. This is guaranteed
	    * by the type Colour.
	    */
	   private boolean verifyNodesColour() {
	      return true; // already implemented
	   }

	   /**
	    * The colour of the root must be black.
	    */
	   private boolean verifyRootColour() {
	      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
	      // implemented
	   }

	   /**
	    * This is guaranteed by the constructor.
	    */
	   private boolean verifyNILNodeColour() {
	      return true; // already implemented
	   }

	   /**
	    * Verifies the property for all RED nodes: the children of a red node must be
	    * BLACK.
	    */
	   private boolean verifyChildrenOfRedNodes() {
	      return verifyChildrenRed((RBNode<T>) this.root);
	   }

	   private boolean verifyChildrenRed(RBNode<T> node) {
	      boolean result = true;
	      if (!node.isEmpty()) {
	         if (node.getColour() == Colour.RED) {
	            RBNode<T> left = (RBNode<T>) node.getLeft();
	            RBNode<T> right = (RBNode<T>) node.getRight();
	            if (left.getColour() == Colour.RED || right.getColour() == Colour.RED) {
	               result = false;
	            }
	         }
	         result = verifyChildrenRed((RBNode<T>) node.getLeft()) && verifyChildrenRed((RBNode<T>) node.getRight());
	      }
	      return result;
	   }

	   /**
	    * Verifies the black-height property from the root.
	    */
	   private boolean verifyBlackHeight() {
	      int left = verifyBlackHeight((RBNode<T>) this.root.getLeft(), 0);
	      int right = verifyBlackHeight((RBNode<T>) this.root.getLeft(), 0);
	      return left == right;
	   }

	   private int verifyBlackHeight(RBNode<T> node, int i) {
	      if (node != null && node.isEmpty()) {
	         if (node.getColour() == Colour.BLACK) {
	            i += 1;
	         }
	         return Math.max(verifyBlackHeight((RBNode<T>) node.getLeft(), i),
	               verifyBlackHeight((RBNode<T>) node.getRight(), i));
	      }
	      return i + 1;
	   }

	   @Override
	   public void insert(T value) {
	      RBNode<T> node = this.insert((RBNode<T>) this.root, value, new RBNode<>());
	      node.setColour(Colour.RED);
	      this.fixUpCase1(node);
	   }

	   private RBNode<T> insert(RBNode<T> node, T element, RBNode<T> parent) {
	      if (node.isEmpty()) {
	         node.setData(element);
	         node.setLeft(new RBNode<T>());
	         node.setRight(new RBNode<T>());
	         node.setParent(parent);
	         return node;
	      } else if (element.compareTo(node.getData()) < 0) {
	         return this.insert((RBNode<T>) node.getLeft(), element, node);
	      } else if (element.compareTo(node.getData()) > 0) {
	         return this.insert((RBNode<T>) node.getRight(), element, node);
	      }
	      return null;
	   }

	   @SuppressWarnings("unchecked")
	@Override
	   public RBNode<T>[] rbPreOrder() {
	      List<RBNode<T>> list = new ArrayList<>();
	      rbPreOrder((RBNode<T>) this.getRoot(), list);
	      RBNode<T>[] array = new RBNode[list.size()];
	      return list.toArray(array);
	   }

	   private void rbPreOrder(RBNode<T> node, List<RBNode<T>> list) {
	      if (!node.isEmpty()) {
	         list.add(node);
	         rbPreOrder((RBNode<T>) node.getLeft(), list);
	         rbPreOrder((RBNode<T>) node.getRight(), list);
	      }
	   }

	   // FIXUP methods
	   protected void fixUpCase1(RBNode<T> node) {
	      if (this.root == node) {
	         node.setColour(Colour.BLACK);
	      } else {
	         fixUpCase2(node);
	      }
	   }

	   protected void fixUpCase2(RBNode<T> node) {
	      if (((RBNode<T>) node.getParent()).getColour() == Colour.BLACK) {

	      } else {
	         fixUpCase3(node);
	      }
	   }

	   protected void fixUpCase3(RBNode<T> node) {
	      RBNode<T> grandPa = (RBNode<T>) node.getParent().getParent();
	      RBNode<T> uncle;

	      if (node.getParent() == grandPa.getRight()) {
	         uncle = (RBNode<T>) grandPa.getLeft();
	      } else {
	         uncle = (RBNode<T>) grandPa.getRight();
	      }

	      if (uncle.getColour() == Colour.RED) {
	         ((RBNode<T>) grandPa.getLeft()).setColour(Colour.BLACK);
	         ((RBNode<T>) grandPa.getRight()).setColour(Colour.BLACK);
	         grandPa.setColour(Colour.RED);
	         fixUpCase1(grandPa);
	      } else {
	         fixUpCase4(node);
	      }
	   }

	   protected void fixUpCase4(RBNode<T> node) {
	      RBNode<T> grandPa = (RBNode<T>) node.getParent().getParent();
	      RBNode<T> next = node;
	      if ((node == node.getParent().getRight()) && (node.getParent() == grandPa.getLeft())) {
	         leftRotation((BSTNode<T>) node.getParent());
	         next = (RBNode<T>) node.getLeft();
	      } else if ((node == node.getParent().getLeft()) && (node.getParent() == grandPa.getRight())) {
	         rightRotation((BSTNode<T>) node.getParent());
	         next = (RBNode<T>) node.getRight();
	      }
	      fixUpCase5(next);
	   }

	   protected void fixUpCase5(RBNode<T> node) {
	      RBNode<T> grandPa = (RBNode<T>) node.getParent().getParent();
	      ((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
	      grandPa.setColour(Colour.RED);
	      if (node == node.getParent().getLeft()) {
	         rightRotation(grandPa);
	      } else {
	         leftRotation(grandPa);
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