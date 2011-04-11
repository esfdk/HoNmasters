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
		List<String> textList = new ArrayList<String>();
		Set<String> textSet = new TreeSet<String>();
		Map<String, Integer> textMap = new TreeMap<String, Integer>();
		
		textList.addAll(Text.getTextAsLinkedList());
		textSet.addAll(textList);
		
		for(String s : textList){
			if(textMap.containsKey(s)){ //If the string already exists in the map, increase the value by one. 
				textMap.put(s, textMap.get(s) + 1);
			}
			else{//If it doesn't exist in the map, put it there with a value of 1.
				textMap.put(s, 1);
			}
		}
		
		//Opgave a
		System.out.println(textList.size()); 
		//Opgave b
		System.out.println(textList.get(45)); //This could also be index 44, depending on how you understand the assignment.
		//Opgave c
		System.out.println(textSet.size());
		//Opgave d
		System.out.println(textSet);
		//Opgave e
		Collections.sort(textList); //Sorts the textList.
		System.out.println(textList);
		//Opgave f
		System.out.println(textMap);
	}
}