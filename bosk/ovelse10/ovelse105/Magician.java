package bosk.ovelse10.ovelse105;

abstract public class Magician extends Player {
	MagicPowers magicPowers;
	
	public Magician(String name, Strength strength, Smartness smartness, MagicPowers magicPowers){
		super(name, strength, smartness);
		this.magicPowers = magicPowers;
	}
	
	public void increaseMagicPowers(){
		int o = magicPowers.ordinal();
		MagicPowers[] values = MagicPowers.values();
		if (o == values.length - 1)
			return;
		else
			magicPowers = values[o+1];
	}
	
	public MagicPowers getMagicPowers(){
		return magicPowers;
	}
	
	public String speak(){
		return "I am " + name + " and I am " + strength + ", " + smartness + " and " + magicPowers;
	}
	
	public String toString(){
		return name;
	}
}
