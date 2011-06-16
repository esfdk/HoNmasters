package bads.aflevering6;

public class Insertion{

	public static void sort(int[] a, int b, int c) {
	    for (int i = b; i <= c; i++) {
	        for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
	            exch(a, j, j-1);
	        }
	    }
	}
	
	private static boolean less(int a, int b){
		return a < b;
	}
	
	private static void exch (int[] a, int i, int j){
		int t = a[i]; 
		a[i] = a[j]; 
		a[j] = t;
	}	
}