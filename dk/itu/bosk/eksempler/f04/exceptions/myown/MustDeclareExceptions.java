package dk.itu.bosk.eksempler.f04.exceptions.myown;

public class MustDeclareExceptions {
	
	public static void main(String[] args){
		
		int i = increase(3);
		
	}
	
	
	public static int increase(int i){
		if(i >= 0)
			return i++;
		else 
			throw new LessThanZeroException("Number must be 0 or greater");
		
	}
}

class LessThanZeroException extends Exception{
	
	public LessThanZeroException(){
		super();
	}
	
	public LessThanZeroException(String msg){
		super(msg);
	}
}


