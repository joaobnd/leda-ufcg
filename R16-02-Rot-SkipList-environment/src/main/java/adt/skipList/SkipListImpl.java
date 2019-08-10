package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> x = this.root;
		
		for(int i = this.maxHeight-1;i>=0;i--) {
			
			while(x.forward[i] != null && x.forward[i].getKey() < key) {
				x = x.forward[i];
			}
			update[i] = x;
		}
		x = x.forward[0];
		if(x.getKey() == key) {
			x.setValue(newValue);
		} else {
			x = new SkipListNode<T>(key, height, newValue);
			for(int i = 0;i<height;i++) {
				x.forward[i] = update[i].forward[i];
				update[i].forward[i] = x;
		}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> x = this.root;
		
		for (int i = this.maxHeight-1;i >= 0;i--) {
			while(x.forward[i] != null && x.forward[i].getKey() < key) {
				x = x.forward[i];
			}
			update[i] = x;
		}
		x = x.forward[0];
		if(x.getKey() == key) {
			int index = 0;
			while (index < this.maxHeight && update[index].forward[index] == x) {
				update[index].forward[index] = x.forward[index];
				index++;
			}
		}
	}
	

	@Override
	public int height() {
		int result = 0;
		SkipListNode<T> x = this.root.forward[0];
		
		while (!x.equals(NIL)) {
			if (x.height() > result) {
				result = x.height();
			}
			x = x.forward[0];
		}
		return result;
	}
	

	@Override
	public SkipListNode<T> search(int key) {
	
		SkipListNode<T> x = this.root;
		
		for (int i=this.maxHeight-1;i>=0;i--) {
			
			while (x.forward[i] != null &&  x.forward[i].getKey() < key) {
				x = x.forward[i];
			}
					}
		x = x.forward[0];
		
		if (x.getKey() == key) {
			return x;
		}
		else {
			return null;
		}
			

	}

	@Override
	public int size() {
		int result = 0;
		SkipListNode<T> x = this.root.forward[0];

		while (!x.equals(NIL)) {
			x = x.forward[0];
			result++;
		}
		return result;
	}

	@Override
	public SkipListNode<T>[] toArray() {

		@SuppressWarnings("unchecked")
		SkipListNode<T>[] array = new SkipListNode[this.size() + 2];
		SkipListNode<T> x = this.root;
	
		int index = 0;
		while (index != this.size() + 2) {
			array[index] = x;
			x = x.forward[0];
			index++;
		}
		return array;
	}
}
