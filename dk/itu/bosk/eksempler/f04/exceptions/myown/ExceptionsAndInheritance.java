package dk.itu.bosk.eksempler.f04.exceptions.myown;

public class ExceptionsAndInheritance {
	
	public void m () throws IllegalArgumentException, MyOtherCheckedException{
		
	}

}

class SubClass extends ExceptionsAndInheritance{


 	// Man beh�ver ikke kaste med de samme Exceptions som superklassen
	@Override
	public void m(){
		
	}
	
	
/*
    // Man m� gerne kaste med nye unchecked Exceptions
	@Override
	public void m() throws ArithmeticException{
		
	}
*/
/*	
    // Man m� ikke kaste med nye checked Exceptions
	@Override
	public void m() throws MyCheckedException{
		
	}
*/
}

class MyCheckedException extends Exception{
}

class MyOtherCheckedException extends Exception{
	
}