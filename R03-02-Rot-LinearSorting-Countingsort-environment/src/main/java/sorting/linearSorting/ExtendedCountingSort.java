package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex<rightIndex && array!=null) {
			int menor = array[leftIndex];
			int maior = array[leftIndex];
			
			for (int i = leftIndex; i < rightIndex + 1; i++) {
				if (array[i] > maior) {
					maior = array[i];
				}
				if (array[i] < menor) {
					menor = array[i];
				}
			}
			Integer[] c = new Integer[maior - menor + 1];

			for (int i = 0; i < c.length; i++) {
				c[i] = 0;
			}

			for (int i = leftIndex; i < rightIndex + 1; i++) {
				c[array[i] - menor] += 1;
			}

			int i = leftIndex;
			int j = leftIndex;

			while (i < c.length) {
				while (c[i] != 0) {
					array[j] = i + menor;
					j += 1;
					c[i] -= 1;
				}
				i += 1;
			}
		}
	}
}