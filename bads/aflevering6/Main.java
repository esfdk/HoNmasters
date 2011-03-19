package bads.aflevering6;
import java.util.Arrays;

import stdlib.StdRandom;
import stdlib.StdStats;
import stdlib.Stopwatch;
public class Main {

	public static void main(String[] args) {
		//Cutoffs to be tested
		int[] cutoffs = {0, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 45, 50, 55, 60, 64};
		int numberOfTests = 20;
		/*
		 * originalArray is used to provide a copy of the array that we sort, so that we try the different cutoffs with the same array. 
		 * We could have used the shuffle function, but we felt that we wanted to have a sort of consistency in our tests.
		 */
		int[] originalArray;
		//sortThis is the array that is actually being sorted
		int[] sortThis;
		double[] experimentTime = new double[numberOfTests];
		//qsMean and qsDev are saved for used in comparisons. expMean is the value used for comparing the latest test with the quicksort result. 
		double qsMean = 0;
		double qsDev = 0;
		double expMean = 0;

		System.out.println("Number of tests = " + numberOfTests);
		for(int N = 100; N <= 1000000; N *= 10){
			originalArray = new int[N]; //Generates new array 
			for(int k = 0; k < N; k++){
				originalArray[k] = StdRandom.uniform(10 * N)+1;
			}

			for(int cutoff : cutoffs){
				
				for(int j = 0; j < numberOfTests; j++){					
					sortThis = Arrays.copyOf(originalArray, N);//Copies originalArray into sortThis.
					
					Stopwatch sw = new Stopwatch();
					QuickInsertion.sort(sortThis, cutoff); //Experiment.
					experimentTime[j] = sw.elapsedTime();
					
					if(!isSorted(sortThis)){ //Stops the program, if it has failed to sort.
						System.exit(0);
					}
					
					if(cutoff == 0){ //QuickSort values.
						qsDev = StdStats.stddev(experimentTime);
						qsMean = StdStats.mean(experimentTime);
					}
					
					expMean = StdStats.mean(experimentTime); //Mean of experiment used in the compare
					
					System.out.println("Cutoff = " + cutoff + "\t| Integers = " + 
							N + "\t| Total = " + StdStats.sum(experimentTime) + "\t| Mean = " + expMean + 
							"\t| stddev = " + StdStats.stddev(experimentTime));
					
					if(qsMean - expMean > qsDev){ //If the improvement in running time is better than the stddev of quicksort (cutoff 0) then this is printed.
						System.out.println("Improvement of " + 100 * ((qsMean-expMean)/qsMean) + " percent.");
					}
					else{ //Else noise is printed.
						System.out.println("Noise!");
					}
				}
			}

		}
	}
	/**
	 * Checks if the array is sorted.
	 * @return True, if array is sorted. False, if not.
	 */
	public static boolean isSorted(int[] a){
		for(int b = 1; b < a.length; b++){
			if(a[b] < a[b-1]) return false;
		}
		return true;
	}

}
