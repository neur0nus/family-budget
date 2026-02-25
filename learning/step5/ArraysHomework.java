package fb2.test;

public class ArraysHomework {
	public static void main(String[] args) {
		//Перестановка элементов массива в обратном порядке
		int[] original = {1, 2, 3, 4, 5};
		int[] reversed = new int[original.length];
		for (int i = 0; i < original.length; i++) {
		    reversed[i] = original[original.length - 1 - i];
		}
		for (int i = 0; i < original.length; i++) {
		    System.out.print(original[i] + " ");
		}
	    System.out.println("---");
		for (int i = 0; i < reversed.length; i++) {
		    System.out.print(reversed[i] + " ");
		}
	    System.out.println("---");
		//Сортировка элементов массива по возрастанию
		int[] array = {5, 2, 8, 1, 9};
		for (int i = 0; i < array.length; i++) {
		    System.out.print(array[i] + " ");
		}
	    System.out.println("---");
		for (int i = 0; i < array.length - 1; i++) {
		    for (int j = 0; j < array.length - 1 - i; j++) {
		        if (array[j] > array[j + 1]) {
		            int temp = array[j];
		            array[j] = array[j + 1];
		            array[j + 1] = temp;
		        }
		    }
		}
		for (int i = 0; i < array.length; i++) {
		    System.out.print(array[i] + " ");
		}
	    System.out.println("---");
		//Перестановка элементов массива в обратном порядке без создания нового массива
		int[] array1 = {1, 2, 3, 4, 5};
		for (int i = 0; i < array1.length; i++) {
		    System.out.print(array1[i] + " ");
		};
	    System.out.println("---");
		for (int i = 0; i < array1.length / 2; i++) {
		    int temp = array1[i];
		    array1[i] = array1[array1.length - 1 - i];
		    array1[array1.length - 1 - i] = temp;
		}
		for (int i = 0; i < array1.length; i++) {
		    System.out.print(array1[i] + " ");
		}
	    System.out.println("---");
	}
}
