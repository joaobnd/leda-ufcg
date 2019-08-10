package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		boolean saida = false;
		if (this.data == null) {
			saida = true;
		}
		return saida;
	}

	@Override
	public int size() {
		if (this.isEmpty()) {
			return 0;
		} else {
			return 1 + this.next.size();
		}
	}

	@Override
	public T search(T element) {
		T saida = null;
		if (this.isEmpty()) {
			
		} else {
			if (this.data.equals(element)) {
				saida = this.data;
			} else {
				saida = this.next.search(element);
			}
		}
		return saida;
	}

	@Override
	public void insert(T element) {
		if (this.isEmpty()) {
			this.data = element;
			this.next = new RecursiveSingleLinkedListImpl<>();
		} else {
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (this.isEmpty()) {
			
		} else {
			if (this.data.equals(element)) {
				this.data = this.next.data;
				this.next = this.next.next;
			} else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] saida = (T[]) new Object[this.size()];
		this.toArray(saida, this, 0);
		return saida;
	}

	private void toArray(T[] saida, RecursiveSingleLinkedListImpl<T> recursiveSingleLinkedListImpl, int i) {
		if (!recursiveSingleLinkedListImpl.isEmpty()) {
			saida[i] = recursiveSingleLinkedListImpl.data;
			toArray(saida,recursiveSingleLinkedListImpl.next, ++i);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
