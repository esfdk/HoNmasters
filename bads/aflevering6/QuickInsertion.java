package bads.aflevering6;
/**
 * 
 * @author Jakob Melnyk
 * @version Vers 1
 */
public class QuickInsertion {
	public static void sort(int a[], int cutoff){
		sort(a, 0, a.length-1, cutoff);
	}
	private static void sort(int[] a, int lo, int hi, int cutoff){
		if(hi <= lo + cutoff){Insertion.sort(a, lo, hi); return;}
		int j = partition(a, lo, hi);
		sort(a, lo, j-1, cutoff);
		sort(a, j+1, hi, cutoff);
	}
	private static int partition(int[] a, int lo, int hi){
		int i = lo, j = hi+1;
		int v = a[lo];
		while(true){
			while(less(a[++i], v)) if (i == hi) break;
			while(less(v, a[--j])) if (j == lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
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
