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
		
		while (!aux.isNIL()) {
			size++;
			aux = aux.getNext();
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
			aux = aux.getNext();
		}
		return node;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> aux = this.head;
		
		if(this.isEmpty()) {
			this.head.setData(element);
			this.head.setNext(new SingleLinkedListNode<T>());
		} else {
			while(!aux.isNIL()) {
				aux = aux.getNext();
			}
			aux.setData(element);
			aux.setNext(new SingleLinkedListNode<T>());
		}
	}

	@Override
	public void remove(T element) {
		
		if(!this.isEmpty()) {
			
			if(this.head.getData().equals(element))
				head = head.next;
			else {
				SingleLinkedListNode<T> aux = this.head;
				
				boolean removido = false;
				while(!aux.getNext().isNIL() && !removido) {
					if(aux.getNext().getData().equals(element)) {
						aux.setNext(aux.getNext().getNext());
						removido = true;
					}
					aux = aux.getNext();
				}
				
			}
		}
	}

	@Override
	public T[] toArray() {
		
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[this.size()];
		
		if(!this.isEmpty()) {
			SingleLinkedListNode<T> aux = this.head;
			int i =0;
			while(!aux.isNIL()) {
				array[i] = aux.getData();
				aux = aux.getNext();
				i++;
			}
			
		}
		return array;
	}
	

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
