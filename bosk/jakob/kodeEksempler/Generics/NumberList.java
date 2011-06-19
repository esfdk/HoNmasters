package bosk.jakob.kodeEksempler.Generics;

import java.util.ArrayList;
import java.util.Collection;

public class NumberList<N extends Number> {
	
	ArrayList<N> coll;
	
	public NumberList(){		
		coll = new ArrayList<N>();
	}
	
	public N removeLast(){
		return coll.remove(coll.size() - 1 );
		
	}
	
	public void add(N number){
		coll.add(number);
	}
	
	public void addCollectionToThisList(Collection<? extends N> c){
		coll.addAll(c);
	}
	
	public void addThisListToCollection(Collection<? super N> c){
		c.addAll(coll);
	}
}
