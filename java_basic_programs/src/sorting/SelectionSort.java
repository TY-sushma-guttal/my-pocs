package sorting;

public class SelectionSort {

	public static void main(String[] args) {
		int[] array = { 3, 1, 5, 6, 3, 4 };
		selectionSort(array);
		for (int i : array) {
			System.err.print(i);
		}
	}

	private static void selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;
			for (int j = i+1; j < array.length; j++) {
				if (array[min] < array[j]) {
					min = j;
				}
			}
			int temp = array[min];
			array[min] = array[i];
			array[i] = temp;
		}
	}

}
