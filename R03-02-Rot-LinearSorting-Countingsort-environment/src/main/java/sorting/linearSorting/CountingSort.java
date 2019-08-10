package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if (array != null && leftIndex < rightIndex) {
			int maxVal = 0;
			
			for (int i = leftIndex; i<rightIndex+1;i++ ) {
				if (array[i] > maxVal) {
					maxVal = array[i];		
				}
			}
			Integer[] c = new Integer[maxVal+1];
			
			for (int i=0; i<c.length;i++) {
				c[i] = 0;
			}
			
			for (int i = 0;i < rightIndex+1;i++) {
				c[array[i]] += 1;
			}
			
			for (int i=1;i<c.length;i++) {
				c[i] = c[i] + c[i-1];
			}
			
			Integer[] b = new Integer[array.length];
			
			for (int i=rightIndex;i>=0;i--) {
				b[c[array[i]]-1] = array[i];
				c[array[i]] -= 1;
			}
			for (int i=0;i<array.length;i++) {
				array[i] = b[i];
			}
			
			
		}
	}

}
