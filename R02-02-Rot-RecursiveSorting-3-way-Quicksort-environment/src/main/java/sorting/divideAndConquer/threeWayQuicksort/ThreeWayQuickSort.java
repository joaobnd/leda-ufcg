package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {
	
	private int partition(T[] array, int leftIndex, int rightIndex) {
		int wall = leftIndex;
		int current = leftIndex;

		while (current != rightIndex) {
			if (array[current].compareTo(array[rightIndex]) < 0) {
				Util.swap(array, wall, current);
				wall++;
			}
			current++;
		}

		Util.swap(array, rightIndex, wall);

		int leftAuxIdx = wall - 1;
		int rightAuxIdx = wall + 1;
		for(int i = leftIndex; i <= rightIndex; i++){
			if(i < wall && array[i] == array[wall]){
				Util.swap(array, i, leftAuxIdx);
				leftAuxIdx--;
			}

			if(i > wall && array[i] == array[wall]){
				Util.swap(array, i, rightAuxIdx);
				rightAuxIdx++;
			}
		}

		
		return wall;
	}


	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array == null) {
			throw new UnsupportedOperationException("The array must not be null.");
		}
		if (leftIndex < rightIndex) {
			int pivot = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivot - 1);
			sort(array, pivot + 1, rightIndex);
		}
	}

}