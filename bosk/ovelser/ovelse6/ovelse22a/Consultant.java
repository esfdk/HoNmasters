package bosk.ovelser.ovelse6.ovelse22a;


public class Consultant extends Person{
	
	private int competence;
	
	public Consultant(String n, int c)
	{
		setName(n);
		competence = c;
	}
	
	public void setCompetence(int c)
	{
		competence = c;
	}
	
	public int getCompetence()
	{
		return competence;
	}
}
