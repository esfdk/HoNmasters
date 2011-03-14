package bosk.ovelser.ovelse6.ovelse22b;

public class ConsultantRole implements Role {
	
private int competence;
	/**
	 * Constructor for ConsultantRole
	 * @param c competence level
	 */
	public ConsultantRole( int c)
	{
		competence = c;
	}
	/**
	 * sets the competence level
	 * @param c competence level
	 */
	public void setCompetence(int c)
	{
		competence = c;
	}
	/**
	 * returns the competence level
	 * @return competence level
	 */
	public int getCompetence()
	{
		return competence;
	}
	/*'
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "Competence: " + competence;
	}

}
