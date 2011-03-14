package bosk.ovelser.ovelse6.ovelse23;
/**
 * A Cell containing text for a spreadsheet.
 * @author Jakob Melnyk
 * @version 1
 */
public class TextCell extends Cell {

	private String value;
	/**
	 * Constructor for class TextCell
	 * @param s The text the Textcell is meant to contain
	 */
	public TextCell(String s){
		value = s;
	}
	/**
	 * Does nothing for the TextCell.
	 * @return 0
	 */
	@Override
	public double getNumberValue() {
		return 0;
	}
	/*'
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return value;
	}
}
