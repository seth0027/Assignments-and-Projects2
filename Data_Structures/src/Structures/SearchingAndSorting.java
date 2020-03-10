package Structures;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SearchingAndSorting {
	int capacity;
	int[] numbers = new int[500];

	public void readNumbers() {
		File numberFile = new File("Numbers.txt");
		try (Scanner input = new Scanner(numberFile);) {
			int i = 0;
			while (input.hasNextInt()) {
				numbers[i] = input.nextInt();
				i++;
				capacity++;
			}
			i--;
			System.out.println("Capacity is " + capacity);
			System.out.println("i is " + i);

		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}

	}

	public void printNumbers(int[] arr) {
		for (int i = 0; i < capacity; i++) {
			System.out.println(i + " : " + arr[i]);
		}
	}

	public void selectionSort(int[] arr) {
		for (int i = 0; i < capacity; i++) {
			int min = i;
			for (int j = i; j < capacity; j++) {
				if (arr[min] > arr[j]) {
					min = j;

				}

			}
			swap(i, min);
		}
	}

	public void bubbleSort(int[] arr) {
		for (int i = capacity - 1; i > 1; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(j, j + 1);

				}
			}
		}
	}
	public void insertionSort(int[] arr) {
		
		for (int i=1;i<capacity;i++) {
			int j=i;
			while(j> 0 && arr[j]<arr[j-1] ) {
				swap(j,j-1);
				j--;
				
			}
		}
	}

	public void swap(int a, int b) {
		int temp;
		temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;

	}

	public void linearSearch(int value, int[] arr) {
		boolean found = false;
		String indxs = "";
		for (int i = 0; i < capacity; i++) {
			if (value == arr[i]) {
				indxs += " " + i;
				found = true;
			}

		}
		if (found)
			System.out.println("Values found at indexes " + indxs);
		else if (!found)
			System.out.println("Sorry nothing ");
	}

	public void binarySearch(int value, int[] arr) {
		int leftHand = 0;
		int rightHand = capacity - 1;
		int middle = 0;
		while (leftHand <= rightHand) {
			middle = (leftHand + rightHand) / 2;
			if (value > arr[middle]) {
				leftHand = middle + 1;
			} else if (value < arr[middle]) {
				rightHand = middle - 1;
			} else

				leftHand = rightHand + 1;
		}
		System.out.println("Index found at " + middle);
	}

	public static void main(String[] args) {
		SearchingAndSorting search = new SearchingAndSorting();
		search.readNumbers();
		// search.printNumbers(search.numbers);
		//search.selectionSort(search.numbers);
		System.out.println("\n\n");
		// search.linearSearch(999,search.numbers);
		// search.bubbleSort(search.numbers);
		search.printNumbers(search.numbers);
		//search.binarySearch(67, search.numbers);
		search.insertionSort(search.numbers);
		System.out.println("\n");
		search.printNumbers(search.numbers);
	}

}
