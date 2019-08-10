package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (array == null || array.length == 0) {
			return null;
		}
		
		return binarySearchFloor(array, x, 0, array.length - 1);
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		if (array == null || array.length == 0) {
			return null;
		}
		
		return binarySearchCeil(array, x, 0, array.length -1);
	}

	private Integer binarySearchFloor(Integer[] array, Integer x, int begin, int end) {
		
		int middle = (begin + end) / 2;
		
		if (begin > end) {
			return array[end];	
		}
		
		if (x < array[middle]) {
			return binarySearchFloor(array, x, begin, middle - 1);
		}
		
		else if(x > array[middle]) {
			return binarySearchFloor(array, x, middle + 1, end);
		}
		
		else {
			return array[middle];
		}
	}
	
	
	private Integer binarySearchCeil(Integer[] array, Integer x, int begin, int end) {
		
		int middle = (begin + end) / 2;
		
		if (begin > end) {
			return array[begin];
		}
		
		if (x < array[middle]) {
			return binarySearchCeil(array, x, begin, middle - 1);
		}
		
		else if (x > array[middle]) {
			return binarySearchCeil(array, x, middle + 1, end);
		}
		
		else {
			return array[middle];
		}
	}
}
