package bads.aflevering6;

public class Sorted {

	/**
	 * @param args
	 */
	public static void main(int[] args) {
		for(int b = 1; b < args.length; b++){
			if(args[b] < args[b-1]) System.out.println("Not sorted!");
		}
	}
}