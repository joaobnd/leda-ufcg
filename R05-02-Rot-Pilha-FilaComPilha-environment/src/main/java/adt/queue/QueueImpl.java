package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if (this.isEmpty()) {
			return null;
		}
		return array[0];
	}

	@Override
	public boolean isEmpty() {
		return (tail == -1);
	}

	@Override
	public boolean isFull() {
		return (tail == array.length-1);
	}

	private void shiftLeft() {
		for (int i=0;i<tail;i++) {
			array[i] = array[i+1];
		}
		tail--;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		tail++;
		array[tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		T aux = array[0];
		this.shiftLeft();
		return aux;
	}

}
