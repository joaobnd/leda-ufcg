package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		//TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}


public void doIt(T elem) {
 		SingleLinkedListNode aux1 = new SingleLinkedListNode();
		SingleLinkedListNode aux2 = head;
			SingleLinkedListNode help = aux2.getNext();
 			aux2.setNext(aux1);
  			aux1 = aux2;
  			aux2 = help;
	}
  		if (!aux1.isNIL()){
			head = aux1;
  		}
 		SingleLinkedListNode novoNode = new SingleLinkedListNode();
 		novoNode.setData(elem);
 		novoNode.setNext(head);
		head = novoNode;
   }