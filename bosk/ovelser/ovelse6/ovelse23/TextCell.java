package bosk.ovelser.ovelse6.ovelse23;

public class TextCell extends Cell {

	private String value;
	
	public TextCell(String s){
		value = s;
	}
	
	@Override
	public double getNumberValue() {
		return 0;
	}
	
	public String toString(){
		return value;
	}
}
