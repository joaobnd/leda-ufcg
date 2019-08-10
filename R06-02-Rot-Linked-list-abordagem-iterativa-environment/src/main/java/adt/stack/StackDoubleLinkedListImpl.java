package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		} else {
			top.insertFirst(element);
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			T exit = ((DoubleLinkedListImpl<T>) top).getHead().getData();
			top.removeFirst();
			return exit;
		}
	}

	@Override
	public T top() {
		return ((DoubleLinkedListImpl<T>) top).getHead().getData();
	}

	@Override
	public boolean isEmpty() {
		boolean exit = false;
		if (top.size() == 0) {
			exit = true;
		}
		return exit;
	}

	@Override
	public boolean isFull() {
		boolean exit = false;
		if (top.size() == size) {
			exit = true;
		}
		return exit;
	}

}