package bads.aflevering7;

public class CompareProteins {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
	}
	
	
	private double calculateDotProduct(int[] a, int[] b){
		double value = 0;
		for(int i = 0; i < a.length; i++)
		{
			value += a[i] * b[i];
		}
		return value;
	}
	
	private double calculateAngleBetweenVectors(int[] a, int[] b, double c){
		double value = 0;
		value = c / (a.length * b.length);
		return Math.cos(value);
	}
}
