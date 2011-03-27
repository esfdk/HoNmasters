package bads.aflevering7;

import java.util.ArrayList;

import stdlib.StdIn;
import stdlib.StdOut;

public class CompareProteins {

	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int k = 20;
		int d = 10000;
		ArrayList<String> races = new ArrayList<String>();
		ArrayList<int[]> profiles = new ArrayList<int[]>();
		races.add(StdIn.readLine());
		profiles.add(calculateProfile(StdIn.readLine(), k, d));
		for(int[] profile : profiles){
			for(int [] profile2 : profiles){
				StdOut.print(compareProfiles(profile, profile2) + ", ");
				StdOut.println();
			}
		}
	}
	
	private static int[] calculateProfile(String protein, int k, int d){
		int[] temp = new int[50000];
		for(int i = 0; i <= protein.length() - k; i++){
			temp[protein.substring(i, i + k - 1).hashCode() % d] ++;
		}
		return temp;
	}
	
	private static double compareProfiles(int[] a, int[] b){
		double temp = calculateDotProduct(a, b);
		return  calculateAngleBetweenVectors(a, b, temp);
	}
	
	private static double calculateDotProduct(int[] a, int[] b){
		double value = 0;
		for(int i = 0; i < a.length; i++)
		{
			value += a[i] * b[i];
		}
		return value;
	}
	
	private static double calculateAngleBetweenVectors(int[] a, int[] b, double c){
		double value = 0;
		value = c / (a.length * b.length);
		return Math.cos(value);
	}
}
