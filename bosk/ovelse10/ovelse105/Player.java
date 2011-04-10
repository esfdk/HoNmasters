package bosk.ovelse10.ovelse105;



abstract public class Player {
	Strength strength;
	Smartness smartness;
	String name;
	
	Player(String name, Strength strength, Smartness smartness){
		this.strength = strength;
		this.smartness = smartness;
		this.name = name;
	}
	
	public void increaseSmartness(){
		int o = smartness.ordinal();
		Smartness[] values = Smartness.values();
		if (o == values.length - 1)
			return;
		else
			smartness = values[o+1];
	}
	
	public void increaseStrength(){
		int o = strength.ordinal();
		Strength[] values = Strength.values();
		if (o == values.length - 1)
			return;
		else
			strength = values[o+1];
	}

	public Smartness getSmartness() {
		return smartness;
	}

	public Strength getStrength() {
		return strength;
	}
	
	public String toString(){
		return name;
	}
	
	public String speak(){
		return "I am " + name + " and I am " + strength + " and " + smartness;
	}
	
	
}
