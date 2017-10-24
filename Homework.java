package assignment2;

import java.util.Arrays;


import jm.JMC;

public class Homework implements JMC{

	public static void main(String[] args) {

//	constructor
	SortingMethods sort = new SortingMethods();
	
//	generate data array with 10 integers
	int[] data = new int[10];
	sort.generateIntArray();
	
	for(int i = 0; i < sort.generateIntArray().length; i ++) {
		data[i] = sort.generateIntArray()[i];
	}
	
//	test three sorting method as below
	
	//original array
	sort.reset();
	System.out.println("The original array is: ");
	System.out.println(Arrays.toString(data));	
	System.out.println(" ");
	
	//insertion sort
	sort.reset();
	System.out.println("After the insertion sort: ");
	System.out.println(Arrays.toString(sort.insertionSort(data)));
	System.out.println(" ");
	sort.toMusic(sort.getPitch(1), sort.getTime());
	

	//selection sort
	sort.reset();
	System.out.println("After the selection sort: ");
	System.out.println(Arrays.toString(sort.selectionSort(data)));
	System.out.println(" ");
	sort.toMusic(sort.getPitch(2), sort.getTime());

	//quick sort
	sort.reset();
	System.out.println("After the quick sort: ");
	sort.quickSort(data);										
	System.out.println(Arrays.toString(data));
	sort.toMusic(sort.getPitch(3), sort.getTime());
// 	all sorting methods work well
	}
	
}
