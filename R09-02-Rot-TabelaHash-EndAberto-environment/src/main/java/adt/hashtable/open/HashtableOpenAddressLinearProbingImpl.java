package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null && this.search(element) == null) {
			int i = 0;
			while (i < this.table.length) {
				int j = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);
				if (this.table[j] == null || (new DELETED()).equals(this.table[j])) {
					this.table[j] = element;
					this.elements++;
					return;
				} else {
					i++;
					this.COLLISIONS++;
				}
			}
			throw new HashtableOverflowException();
		}
	}

	@Override
	public void remove(T element) {
		int i = 0;
		while (i < this.table.length) {
			int aux = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);
			if (this.table[aux] == null) {
				break;
			} else if (element.equals(this.table[aux])) {
				this.table[aux] = new DELETED();
				this.elements--;
			} else {
				i++;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		int i = 0;
		T saida = null;
		while (i < this.table.length) {
			int aux = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);
			if (this.table[aux] == null) {
				break;
			} else if (element.equals(this.table[aux])) {
				saida = (T) this.table[aux];
				break;
			}
			i++;
		}
		return saida;
	}

	@Override
	public int indexOf(T element) {
		int i = 0;
		int saida = -1;
		while (i < this.table.length) {
			int aux = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);
			if (this.table[aux] == null) {
				break;
			} else if (element.equals(this.table[aux])) {
				saida = aux;
				break;
			} else {
				i++;
			}
		}
		return saida;
	}

}
