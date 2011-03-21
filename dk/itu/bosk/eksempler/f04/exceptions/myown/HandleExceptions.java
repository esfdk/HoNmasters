package dk.itu.bosk.eksempler.f04.exceptions.myown;

public class HandleExceptions {
	
	public static void m(int i) throws MyException{
		if (i < 0)
			throw new MyException("kastet fra m");
	}

	public static void n(int i) throws MyOtherException{
		try{
			m(i);
		}
		catch(MyException me){
			throw new MyOtherException("kastet fra n", me);
		}
	}
	
	public static void main (String[] args){
		
		try{
			n(-1);
		}
		catch(MyOtherException moe){
			moe.printStackTrace();
		}
		
	}
}

class MyException extends Exception{
	
	MyException(){
		super();
	}
	
	MyException(String msg){
		super(msg);
	}
	
	MyException(Throwable cause){
		super(cause);
	}
	
	MyException(String msg, Throwable cause){
		super(msg, cause);
	}
}

class MyOtherException extends Exception{
	
	MyOtherException(){
		super();
	}
	
	MyOtherException(String msg){
		super(msg);
	}
	
	MyOtherException(Throwable cause){
		super(cause);
	}
	
	MyOtherException(String msg, Throwable cause){
		super(msg, cause);
	}
}