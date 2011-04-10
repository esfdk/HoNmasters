package bosk.ovelse10.ovelse101;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class TextAnalyzer {
	
	public static void main(String[] args){
		List<String> tl = new ArrayList<String>();
		Set<String> ts = new TreeSet<String>();
		Map<String, Integer> tm = new TreeMap<String, Integer>();
		
		tl.addAll(Text.getTextAsLinkedList());
		ts.addAll(tl);
		
		for(String s : tl){
			if(tm.containsKey(s)){
				tm.put(s, tm.get(s) + 1);
			}
			else{
				tm.put(s, 1);
			}
		}
		
		System.out.println(tl.size());
		System.out.println(tl.get(45)); //This could also be index 44, depending on how you understand the assignment.
		System.out.println(ts.size());
		System.out.println(ts);
		Collections.sort(tl);
		System.out.println(tl);
		System.out.println(tm);
	}

}
