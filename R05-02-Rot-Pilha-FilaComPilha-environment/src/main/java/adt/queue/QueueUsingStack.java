package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull())
			throw new QueueOverflowException();
		
		try {
			this.stack1.push(element);
			
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty())
			throw new QueueUnderflowException();
		/** passa elementos da pilha 1 pra pilha 2.
		 *  remove o topo da pilha 2 e devolve os elementos pra pilha1.
		 */
		try {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		} catch (StackOverflowException | StackUnderflowException e1) {
			e1.printStackTrace();
		}
		
		T value = null;
		
		try {
			value = stack2.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		
		try {
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
		} catch (StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		
		return value;
	}

	@Override
	public T head() {
		/**
		 * Passa os elementos da pilha 1 pra pilha 2,
		 * atribui o valor do topo da pilha 2 a variavel head,
		 * devolve os elementos pra pilha 1.
		 */
		
		try {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());;
			}
		} catch (StackOverflowException | StackUnderflowException e1) {
			e1.printStackTrace();
		}
		
		
		T element = stack2.top();
		
		try {
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
		} catch (StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		
		return element;
		
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack2.isFull();
	}

}
