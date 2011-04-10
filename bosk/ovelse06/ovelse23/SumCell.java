package bosk.ovelse06.ovelse23;
/**
 * Used to calculate the sum of two or more cells in a spreadsheet.
 * @author Jakob Melnyk
 * @version Vers 1
 */
public class SumCell extends Cell {

	private double value = 0;
	/**
	 * Constructor for class SumCell
	 * @param a First cell
	 * @param b Second cell
	 */
	public SumCell(Cell a, Cell b){
		value += a.getNumberValue();
		value += b.getNumberValue();
	}
	/**
	 * Constructor for class SumCell
	 * @param a Array of cells that needs to be summed.
	 */
	public SumCell(Cell[] a){
		for(Cell b : a){
			value += b.getNumberValue();
		}
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
