package bads.aflevering6;
import java.util.Arrays;

import stdlib.StdRandom;
import stdlib.StdStats;
import stdlib.Stopwatch;
public class Main {

	public static void main(String[] args) {
		int[] cutoffs = {0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 25, 30, 32, 36, 40, 64};
		int antalTests = 20;
		int[] originalArray;
		int[] sortThis;
		double[] experimentTime = new double[antalTests];
		double qsMean = 0;
		double qsDev = 0;
		double expMean;
		
		
		System.out.println("Number of tests = " + antalTests);
		for(int N = 100; N <= 1000000; N *= 10){
			System.out.println();
			originalArray = new int[N];
			for(int k = 0; k < N; k++){
				originalArray[k] = StdRandom.uniform(10 * N)+1;
			}
			
			for(int cutoff : cutoffs){
				for(int j = 0; j < antalTests; j++){
					sortThis = Arrays.copyOf(originalArray, N);
					Stopwatch sw = new Stopwatch();
					QuickInsertion.sort(sortThis, 0);
					experimentTime[j] = sw.elapsedTime();
				}
				if(cutoff == 0){
					qsDev = StdStats.stddev(experimentTime);
					qsMean = StdStats.mean(experimentTime);
				}
				expMean = StdStats.mean(experimentTime);
				System.out.println("Cutoff = " + cutoff + "\t| Integers = " + 
						N + "\t| Total = " + StdStats.sum(experimentTime) + "\t| Mean = " + expMean + 
						"\t| stddev = " + StdStats.stddev(experimentTime));
				if(qsMean - expMean > qsDev)System.out.println("Improvement of " + 100 * ((qsMean-expMean)/qsMean) + " percent."); else if(qsMean - expMean <= qsDev) System.out.println("Noise!");
			}
		}
	}
}
