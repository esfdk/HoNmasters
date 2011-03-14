package bosk.ovelser.ovelse6.ovelse22b;

public class ConsultantRole implements Role {
	
private int competence;
	
	public ConsultantRole( int c)
	{
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
	
	public String toString()
	{
		return "Competence: " + competence;
	}

}
