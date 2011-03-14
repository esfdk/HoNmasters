package bosk.ovelser.ovelse6.ovelse23;
/**
 * A floating point cell used in spreadsheet
 * @author Jakob Melnyk
 * @version Vers 1
 */
public class FloatingPointCell extends Cell{

	private double value;
	
	/**
	 * Constructor for class FloatingPointCell
	 * @param v The value of the number in the cell
	 */
	public FloatingPointCell(double v){
		value = v;
	}
	/**
	 * Gets the value of the number in the cell.
	 * @return The value of the number in the cell.
	 */
	@Override
	public double getNumberValue() {
		return value;
	}
	/*'
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return Double.toString(value);
	}
}
