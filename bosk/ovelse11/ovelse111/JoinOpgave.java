package bosk.ovelse11.ovelse111;
/*
Skal bruges i forbindelse med opgave 9.1.
*/
public class JoinOpgave{
	public static void main( String[ ] args ){
		System.out.println( "main started" );
		final Thread t1 = new Thread( ){
			public void run( ){
				System.out.println( "t1 started" );
				try{ 
					Thread.sleep( 2000 );
				}
				catch( InterruptedException e ){ 
				}				
				System.out.println( "t1 finishes" );				
			}
		};
		System.out.println( "t1 made" );
		final Thread t2 = new Thread( ){
			public void run( ){
				System.out.println( "t2 started" );
				try{ 
					t1.join( ); 
				}
				catch( InterruptedException e ){ 
				}				
				System.out.println( "t2 finishes" );				
			}
		};
		System.out.println( "t2 made" );
		t1.start( );
		t2.start( );
		try{ 
			t2.join( ); 
		}
		catch( InterruptedException e ){ 
		}				
		System.out.println( "main finishes" );
	}
}