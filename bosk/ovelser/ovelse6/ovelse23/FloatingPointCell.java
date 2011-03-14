package bosk.ovelser.ovelse6.ovelse23;

public class FloatingPointCell extends Cell{

	private double value;
	
	public FloatingPointCell(double v){
		value = v;
	}
	
	@Override
	public double getNumberValue() {
		return value;
	}
	
	public String toString(){
		return Double.toString(value);
	}
}
