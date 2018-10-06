// **********************************************************
// Assignment0:
// UTORID: gadriwa1
// UT Student #: 1004351051
// Author: gadriwa1
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences. In this semester
// we will select any three of your assignments from total of 5 and run it
// for plagiarism check. 
// *********************************************************
package a0;

import java.util.Arrays;

/**
 * User can rearrange the elements in the instance of Rearranging
 */
public class Rearranging {
	/**
	 * Returns a rearranged array with the following guideline:
	 * [-ve numbers, 0's, +ve numbers]
	 * @param items An array of unordered numbers 
	 */
	public static void rearranging(int[] items)
	{
	  /* given an array, make two pointers point to the starting of the array
	  * (the main and negative pointer) and make a pointer point to the 
	  * end of the array (the positive pointer)
	  * 1- check if the main element is +ve, if it is, swap it with the
	  * positive pointer and decrease the positive pointer by one
	  * 2- check if the main element is -ve, if it is, swap it with the
	  * negative pointer and increase it by 1
	  * 3- check if the main element is 0, if it is do nothing and increase
	  * the main pointer by 1
	  * Terminate the loop when index(main) == index(positive)
	  */
	  // initiating the pointers
	  int negPointer = 0; // points to the start
	  int mainPointer = 0; // points to the start
	  int posPointer = items.length - 1; // points to the end
	  // setting up the loop
	  while (mainPointer != posPointer + 1) {
	    // the positive case
	    if (items[mainPointer] > 0) {
	      // swapping with positive pointer
	      swap(mainPointer, posPointer, items);
	      // decreasing the pointer by 1
	      posPointer --;
	    } // negative case
	    else if (items[mainPointer] < 0) {
	      // swapping the main pointer with the negative pointer 
	      swap(mainPointer, negPointer, items);
	      // increasing the main pointer and negative pointer
	      negPointer ++;
	      mainPointer ++;
	    }
	    // the 0 case; do nothing but increasing the pointer
	    else if (items[mainPointer] == 0) {
	      mainPointer++;
	    }
	  }
	}
	
	/**
	 * Returns an array with elements swapped at i and j
	 * @param i Initial index
	 * @param j Final index
	 * @param items array of unarranged numbers
	 */
	private static void swap(int i,int j,int[] items)
	{
      // i is the current index
      // j is the new index aka index where we
      // wanna swap the element at
      // store the curr element in a temp var
      // so that it doesn't get replaced
      int temp = items[i];
      // swap the elements
      items[i] = items[j];
      items[j] = temp;
	}

	public static void main(String[] args) {
		int [] items={-6, 6, 7, -7, 0, -5, 8, 5};
		/*
		 * printing the values in the items before 
		 * calling the method rearranging
		 */
		System.out.println(Arrays.toString(items));
		//calling the rearranging method
		Rearranging.rearranging(items);
		/*
		 * printing the values in the items after 
		 * calling the method rearranging
		 */
		System.out.println(Arrays.toString(items));
	}
}