package adt.bst;

@SuppressWarnings("unchecked")
public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.getRoot());
	}
	
	public int height(BSTNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		} else {
			int height;
			int right = height((BSTNode<T>) node.getRight());
			int left = height((BSTNode<T>) node.getLeft());
			if (right >= left) {
				height = right;
			} else {
				height = left;
			}
			return 1 + height;
		}
	}
	
	@Override
	public BSTNode<T> search(T element) {
		if (element == null || this.getRoot().isEmpty()) {
			return new BSTNode<T>();
		} else {
			return search(element, this.getRoot());
		}
	}
	
	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (node.getData().equals(element)) {
			return node;
		} else if (!node.getLeft().isEmpty() && node.getData().compareTo(element) > 0) {
			return search(element, (BSTNode<T>) node.getLeft());
		} else if (!node.getRight().isEmpty() && node.getData().compareTo(element) < 0){
			return search(element, (BSTNode<T>) node.getRight());
		} else {
			return new BSTNode<T>();
		}
    }
	
	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, this.getRoot());
		}
	}
	
	protected BSTNode<T> insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
			
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
		} else {
			if (element.compareTo(node.getData()) != 0) {
				if (element.compareTo(node.getData()) < 0) {
					node = insert(element, (BSTNode<T>) node.getLeft());
				} else {
					node = insert(element, (BSTNode<T>) node.getRight());
				}
			}
		}
		return node;
	}
	
	@Override
	public BSTNode<T> maximum() {
		return maximum(this.getRoot());
	}
	
	private BSTNode<T> maximum(BSTNode<T> node) {
		if (this.getRoot().isEmpty()) {
			return null;
		} else if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}
	
	@Override
	public BSTNode<T> minimum() {
		return minimum(this.getRoot());
	}
	
	private BSTNode<T> minimum(BSTNode<T> node) {
		if (this.getRoot().isEmpty()) {
			return null;
		} else if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}
	
	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (this.getRoot().isEmpty() || node.getData() == null) {
			return null;
		} else if (!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		} else {
			return sucessor(element, node);
		}
	}
	
	private BSTNode<T> sucessor(T element, BSTNode<T> node) {
		if (node.getParent() != null && (node.getParent().getData().compareTo(element) < 0)) {
			return sucessor(element, (BSTNode<T>) node.getParent());
		} else {
			return (BSTNode<T>) node.getParent();
		}
	}
	
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (this.getRoot().isEmpty() || node.getData() == null) {
			return null;
		} else if (!node.getLeft().isEmpty()) {
			return maximum((BSTNode<T>) node.getLeft());
		} else {
			return predecessor(element, node);
		}
	}
	
	private BSTNode<T> predecessor(T element, BSTNode<T> node) {
		if (node.getParent() != null && (node.getParent().getData().compareTo(element) > 0)) {
			return predecessor(element, (BSTNode<T>) node.getParent());
		} else {
			return (BSTNode<T>) node.getParent();
		}
	}
	
	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if ((!node.isEmpty()) && (element != null)) {
			remove(node);
		}
	}

	protected BSTNode<T> remove(BSTNode<T> node) {
		if (node.getRight().isEmpty() && node.getLeft().isEmpty()) {
			node.setData(null);
			node.setLeft(null);
			node.setRight(null);
		} else if (node.getRight().isEmpty()) {
			node.setData(node.getLeft().getData());
			node.setRight(node.getLeft().getRight());
			node.setLeft(node.getLeft().getLeft());
			node.getRight().setParent(node);
			node.getLeft().setParent(node);
		} else if (node.getLeft().isEmpty()) {
			node.setData(node.getRight().getData());
			node.setLeft(node.getRight().getLeft());
			node.setRight(node.getRight().getRight());
			node.getRight().setParent(node);
			node.getLeft().setParent(node);
		} else {
			T value = node.getData();
			BSTNode<T> sucessor = sucessor(value);
			node.setData(sucessor.getData());
			sucessor.setData(value);
			remove((BSTNode<T>) sucessor);
		}
		
		return node;
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.getRoot().isEmpty()) {
			return array;
		}
		preOrder(array, this.getRoot());
		return array;
	}
	
	
	private void preOrder(T[] array, BSTNode<T> node) {
		add(array, node.getData(), 0);
		if (node.getLeft().getData() != null) {
        	preOrder(array, (BSTNode<T>) node.getLeft());
        }
		if (node.getRight().getData() != null) {
        	preOrder(array, (BSTNode<T>) node.getRight());
        }
    }
	
	private void add(T[] array, T element, int count) {
		if (array[count] == null) {
			array[count] = element;
		} else {
			add(array, element, count+1);
		}
	}
	
	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.getRoot().isEmpty()) {
			return array;
		}
		order(array, this.getRoot());
		return array;
	}

	private void order(T[] array, BSTNode<T> node) {
		if (node.getLeft().getData() != null) {
        	order(array, (BSTNode<T>) node.getLeft());
        }
		add(array, node.getData(), 0);
		if (node.getRight().getData() != null) {
        	order(array, (BSTNode<T>) node.getRight());
        }
    }
	
	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.getRoot().isEmpty()) {
			return array;
		}
		postOrder(array, this.getRoot());
		return array;
	}

	private void postOrder(T[] array, BSTNode<T> node) {
		if (node.getLeft().getData() != null) {
			postOrder(array, (BSTNode<T>) node.getLeft());
        }
		if (node.getRight().getData() != null) {
			postOrder(array, (BSTNode<T>) node.getRight());
        }
		add(array, node.getData(), 0);
    }
	
	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}
