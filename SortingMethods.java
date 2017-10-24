package assignment2;

import java.util.ArrayList;
import java.util.Random;
import jm.music. data.*;
import jm.util.Play;
import jm.JMC;

public class SortingMethods implements JMC{
	private long start;			//the time when a sorting method start
	private long elapsed;		//the time used by a sorting method 
	private int [] noteSet = {C2, C3, C4, D4, G4, A4, BS4, BS5, BS6, BS7 };

//	those Arraylists are used to store the pitches  	
	private ArrayList<Integer> pitch_insert = new ArrayList<Integer>(); 
	private ArrayList<Integer> pitch_select = new ArrayList<Integer>(); 
	private ArrayList<Integer> pitch_quick = new ArrayList<Integer>(); 
	
	
	
	public SortingMethods() {
		start = 0;
		elapsed = 0;
	}
	
	public void reset() { 
		start = 0;
		elapsed = 0;
	}
	
	public ArrayList<Integer> getPitch(int a) {
		if (a == 1) return pitch_insert;
		else if (a == 2) return pitch_select;
		else if (a == 3) return pitch_quick; 
		else return null; 
	}
	
	public long getTime() {
		return elapsed;
	}
	
	
	
//	generation a random integer array, data[]	
	public int[] generateIntArray() {
			Random generator = new Random(30);
			int[] x=new int[10];
			for (int i=0; i<x.length;i++){
				int randomNumber=(int) (generator.nextDouble()*10) +1;
				x[i]=randomNumber;
			}			
			return x;
	}
	
//	insertion sort	
	public int[] insertionSort(int[] arr) {
		start = System.nanoTime();
		int temp;
		int size = arr.length;		
		for (int i = 1; i < size; i++){
			int j = i;
			temp = arr[i];
			while (j > 0 && temp < arr[j - 1]){
				arr[j] = arr[j - 1]; 
				j--;
//	when there is a pass, create a note and add the note to the Arraylist				
				for(int k = 0; k < size-1; k++) {
					pitch_insert.add(noteSet[arr[k]-1]);
				}	
			}
			arr[j] = temp;
		}
//	print out time spent on the sorting method		
		elapsed = System.nanoTime() - start;
		System.out.println("time : " + elapsed);
		System.out.println("pitch_insert : " + pitch_insert.size());
		return arr;
		
	}
	
//	selection sort	
	public int[] selectionSort(int[] arr) {
		start = System.nanoTime();
		int temp;
		int min;
		int size = arr.length;
		for (int i = 0; i < size; i++){		
			min = i; 
			for (int j = i + 1; j < size; j++) { 
				if (arr[j] < arr[min]) {
					min = j;
				} 	
			} 
			temp = arr[min];
			arr[min] = arr[i]; 
			arr[i] = temp;
//	create note when there is a pass			
			for(int k = 0; k < size-1; k++) {
				pitch_select.add(noteSet[arr[k]-1]);
			}
			
		}
//	print out time		
		elapsed = System.nanoTime() - start;
		System.out.println("time : " + elapsed);
		System.out.println("pitch_select : " + pitch_select.size());
		return arr;	
	}
	
//	quick sort override 	
	public void quickSort(int[] arr) {
		start = System.nanoTime();
		quickSort(arr, 0, arr.length-1);
		elapsed = System.nanoTime() - start; 
	    System.out.println("time : " + elapsed);
	    System.out.println("pitch_quick : " + pitch_quick.size());
		
		
	}
//	actual quick sort 
	public void quickSort(int[] arr, int left, int right) {
		int index = partition(arr, left, right);
	      if (left < index - 1)  quickSort(arr, left, index - 1);
	      if (index < right) quickSort(arr, index, right);
	}
	
//	partition 	
	public int partition(int[] arr, int left, int right) {
		 int i = left, j = right;
		 int size = arr.length;
	     int pivot = arr[(left + right) / 2];
	     
	      while (i <= j) {
	            while (arr[i] < pivot) i++;
	            while (arr[j] > pivot) j--;
	            if (i <= j) {
	            		int temp = arr[i];
	            		arr[i]=arr[j];
	            		arr[j]=temp;
	                  i++;
	                  j--;
//	create a note when there is a pass	                  
	                  for(int k = 0; k < size-1; k++) {
	  					pitch_quick.add(noteSet[arr[k]-1]);
	  				}
	            }
	      }
	      return i;
	}
	
//	create a note 	
	public void toMusic(ArrayList<Integer> a, long time) {
//	the pitches are stored as Arraylists
//	copy Arraylists to arrays 		
		int[] pitch = new int[a.size()];
		for(int i = 0; i < a.size(); i++) {
			pitch[i] = (int)a.get(i);
		}
//	relate duration to the time		
		double dur;
		if(time < 20000) dur = HN;
		else if (20000 <= time && time < 100000) dur = QN;
		else dur = SQ;
		
//	construct a phrase			
		Phrase phr = new Phrase();		
		for (int i = 0; i < pitch.length; i++) {
			phr.addNote(new Note(pitch[i],dur));
		}
		phr.addNote(new Note(REST, WN));
		Play.midi(phr);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
