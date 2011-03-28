package bads.aflevering7;

import stdlib.StdIn;
import stdlib.StdOut;
import java.util.ArrayList;

public class CompareProteins {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int k = 20;
		int d = 10000;
		ArrayList<String> races = new ArrayList<String>();
		ArrayList<int[]> profiles = new ArrayList<int[]>();
		while(!StdIn.isEmpty()){
			races.add(StdIn.readLine());
			profiles.add(calculateProfile(StdIn.readLine() + StdIn.readLine() + StdIn.readLine(), k, d));
		}
		StdOut.println(races.size() + ", " + profiles.size());
		for(int i = 0; i < profiles.size(); i++){
			StdOut.println(races.get(i));
			for(int j = 0; j < profiles.size(); j++){
				StdOut.print("\t" + races.get(j) + " " + compareProfiles(profiles.get(i), profiles.get(j)) + ", ");
				StdOut.println();
			}
			StdOut.println();
		}
	}
	
	private static int[] calculateProfile(String protein, int k, int d){
		int[] temp = new int[50000];
		for(int i = 0; i <= protein.length() - k; i++){
			temp[Math.abs(protein.substring(i, i + k - 1).hashCode()) % d] ++;
		}
		return temp;
	}
	
	private static double compareProfiles(int[] a, int[] b){
		double c = calculateDotProduct(a, b);
		double d = calculateVectorLength(a);
		double e = calculateVectorLength(b);
		return  calculateAngleBetweenVectors(a, b, c, d, e);
	}
	
	private static double calculateDotProduct(int[] a, int[] b){
		double value = 0;
		for(int i = 0; i < a.length; i++)
		{
			value += a[i] * b[i];
		}
		return value;
	}
	
	private static double calculateVectorLength(int[] a){
		double temp = 0;
		for(int i : a){
			temp += Math.pow(i, 2);
		}
		return Math.sqrt(temp);
	}
	private static double calculateAngleBetweenVectors(int[] a, int[] b, double c, double d, double e){
		double value = 0;
		value = c / (d * e);
		return value;
	}
}
