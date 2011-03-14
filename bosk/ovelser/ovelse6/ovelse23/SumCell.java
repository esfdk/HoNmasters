package bosk.ovelser.ovelse6.ovelse23;

public class SumCell extends Cell {

	private double value = 0;
	
	public SumCell(Cell a, Cell b){
		value += a.getNumberValue();
		value += b.getNumberValue();
	}
	
	public SumCell(Cell[] a){
		for(Cell b : a){
			value += b.getNumberValue();
		}
	}
	
	@Override
	public double getNumberValue() {
		return value;
	}
	
	public String toString(){
		return Double.toString(value);
	}
}
