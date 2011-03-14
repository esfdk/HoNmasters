package bosk.ovelser.ovelse6.ovelse22a;


public class Consultant extends Person{
	
	private int competence;
	
	/**
	 * Constructor for Consultant
	 * @param n name of the person
	 * @param c compentence level for person
	 */
	public Consultant(String n, int c)
	{
		setName(n);
		competence = c;
	}
	/**
	 * sets the competence for a consultant
	 * @param c competence level for person
	 */
	public void setCompetence(int c)
	{
		competence = c;
	}
	/**
	 * returns the competence level for a consultant
	 * @return return competence level
	 */
	public int getCompetence()
	{
		return competence;
	}
}
