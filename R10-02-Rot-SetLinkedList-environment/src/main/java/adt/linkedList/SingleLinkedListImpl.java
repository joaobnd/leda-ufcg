package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return (head == null);
	}
	
	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = this.head;
		
		if(!aux.isNIL()) {
			size++;
			aux = aux.next;
		}
		return size;
	}

	@Override
	public T search(T element) {
		T node = null;
		SingleLinkedListNode<T> aux = this.head;
		
		while(!aux.isNIL()) {
			if(aux.getData().equals(element))
				node = aux.getData();
			aux = aux.next;
		}
		return node;
	}

	@Override
	public void insert(T element) {
		
		if (element != null) {
			
			if (this.isEmpty()) {
				this.head.setData(element);
				this.head.setNext(new SingleLinkedListNode<T>());
			} else {
				SingleLinkedListNode<T> aux = this.head;
				
				while (!aux.isNIL()) {
					aux = aux.getNext();
				}
				aux.setData(element);
				aux.setNext(new SingleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			if(head.getData().equals(element))
				head = head.next;
		} else {
			SingleLinkedListNode<T> aux = this.head;
			
			boolean done = false;
			while(!aux.getNext().isNIL() && !done) {
				if (aux.getNext().getData().equals(element)) {
					aux.setNext(aux.getNext().getNext());
					done = true;
				}
				aux = aux.getNext();
			}
		}
	}

	@Override
	public T[] toArray() {
		SingleLinkedListNode<T> aux = this.head;
		
		@SuppressWarnings("unchecked")
		T[] toStr = (T[]) new Comparable[this.size()];
		
		int i = 0;
		if(!this.isEmpty()) {
			while (!aux.isNIL()) {
				toStr[i] = aux.getData();
				aux = aux.getNext();
				i++;
			}
		}
		return toStr;
		
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
