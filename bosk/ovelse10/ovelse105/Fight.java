package bosk.ovelse10.ovelse105;

public class Fight<T> {

	T one;
	T two;
	T winner;

	Fight(T one, T two, T winner){
		this.one = one;
		this.two = two;
		this.winner = winner;
	}

	public Object getWinner(){
		return winner;
	}

	public String toString(){
		return "Fight between " + one + " and " + two + ". " + winner + " wins";
	}

}
