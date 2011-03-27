package bosk.ovelser.ovelse8.ovelse81;

public class TestOwnExceptions {

	public static void main(String[] args) {

		System.out.println("Kalder throwMyCheckedException");
		try {
			throwMyCheckedException();
		} catch (MyCheckedException e) {
			System.out.println("Haha, caught you!");
		}
		
		System.out.println("Kalder throwMyUncheckedException");
		try{
			throwMyUncheckedException();
		}catch(Exception e){
			System.out.println("Haha, caught another one!");
		}
	}

	/**
	 * Forces a CheckedException to be thrown.
	 * 
	 * @throws MyCheckedException
	 */
	public static void throwMyCheckedException() throws MyCheckedException {
		throw new MyCheckedException();
	}

	/**
	 * Forces an UnException to be thrown.
	 * 
	 * @throws MyUncheckedException
	 */
	public static void throwMyUncheckedException() throws MyUncheckedException {
		throw new MyUncheckedException();
	}
}
