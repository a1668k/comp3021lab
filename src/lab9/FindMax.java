package lab9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	public static void main(String[] args) {
		new FindMax().runTest();
	}
	
	public void runTest() {
		PrintMax[] printMax = new PrintMax[3];
		printMax[0] = new PrintMax(0, 29);
		printMax[1] = new PrintMax(30, 59);
		printMax[2] = new PrintMax(60, 89);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 3; i++) {
			executor.execute(printMax[i]);
		}
		executor.shutdown();
		
		int total_max = printMax[0].max;
		for (int i = 1; i < 3; i++) {
			if (printMax[i].max > total_max)
				total_max = printMax[i].max;
		}
		System.out.println("the max value is " + total_max);
	}
	
	class PrintMax implements Runnable {
		private int begin;
		private int end;
		int max;
		
		public PrintMax(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			max = findMax(begin, end);
		}
		
	}
	
	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}




