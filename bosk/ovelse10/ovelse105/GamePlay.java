package bosk.ovelse10.ovelse105;

import java.util.ArrayList;
import java.util.List;

import static bosk.ovelse10.ovelse105.MagicPowers.*;
import static bosk.ovelse10.ovelse105.Smartness.*;
import static bosk.ovelse10.ovelse105.Strength.*;
public class GamePlay {

	// Lister der holder styr p� historik - se metoden update() i GameUtils

	//	 listen fightparticipants gemmer historik over hvilke spillere der har k�mpet mod hinanden
	static List<Fight<Player>> fightparticipants = new ArrayList<Fight<Player>>();

	//	 listen fighttypes gemmer historik over hvilke typer af spillere (Thief, Wizard, etc) der har k�mpet mod hinanden.
	static List<Fight<String>>  fighttypes = new ArrayList<Fight<String>>();

	//	 listen smartfight gemmer historik over hvordan det g�r to k�mpende med hver deres intelligensniveau. Kan bruges til at
	// regulere i spillet s� vi ikke f�r for mange stupide vindere :-)
	static List<Fight<Smartness>> smartfight = new ArrayList<Fight<Smartness>>();


	static List<Fight<Strength>>  strongfight = new ArrayList<Fight<Strength>>();

	// Figurerne i spillet
	static List players = new ArrayList();

	public static void main(String[] args) {
		// Vi laver nogle figurer og giver dem nogle egenskaber (massiv brug af enums)
		Warrior knud = new Warrior("Knud", WEAK, CLEVER );
		Warrior svend = new Warrior("Svend", STRONG, STUPID);
		Warrior valdemar = new Warrior("Valdemar", MEDIUM, GENIUS);

		Thief x = new Thief("X", WEAK, STUPID);
		Thief y = new Thief("Y", STRONG, GENIUS);

		Wizard merlin = new Wizard("Merlin", STRONG, GENIUS, SKILLED);
		Wizard andrew = new Wizard("Andrew", WEAK, STUPID, POWERFULL);

		Witch belzebub = new Witch("Belzebub", MEDIUM, GENIUS, NOVICE);
		Witch jadis = new Witch("Jadis", SUPERSTRONG, CLEVER, SUPERPOWERFULL);

		// Alle figurer gemmes i en Collection
		players.add(knud);
		players.add(svend);
		players.add(valdemar);
		players.add(merlin);
		players.add(andrew);
		players.add(x);
		players.add(y);
		players.add(belzebub);
		players.add(jadis);

		GameUtil.printAllPlayers(players);

		System.out.println();

		// Nu starter spillet :-) Masser af kampe. Opgaven er at fjerne al
		// casting i nedenst�ende, dvs i forbindelse med at der kaldes til
		// metoderne fightDirty(), fightSmart() og fightMagical().

		// to argumenter med typen Warrior giver Warrior som returtype
		// S�rg for at vi ikke beh�ver at caste
		System.out.println("Knud and Svend fights dirty:");
		Warrior w1  = (Warrior) GameUtil.fightDirty(knud, svend);
		System.out.println("The winner says: " + w1.speak());

		System.out.println();

		// to argumenter med typen Wizard giver Wizard som returtype
		// S�rg for at vi ikke beh�ver at caste
		System.out.println("Merlin and Andrew fights dirty:");
		Wizard w2 = (Wizard) GameUtil.fightDirty(merlin, andrew);
		System.out.println("The winner says: " + w2.speak());

		System.out.println();

		// blandede argumenter giver Player som returtype
		// Her er der i forvejen intet cast, s� det skal ikke �ndres
		System.out.println("X and Belzebub fights smart:");
		Player p = GameUtil.fightSmart(x, belzebub);
		System.out.println("The winner says: " + p.speak() );

		System.out.println();

		// to slags Magician kan blive til Player eller Magician
		// S�rg for at vi ikke beh�ver at caste til en Magician
		System.out.println("Jadis and Merlin fight magical:");
		Magician m = (Magician) GameUtil.fightMagical(jadis, merlin);
		Player mp = GameUtil.fightMagical(jadis, merlin);
		// men vi kan ikke vide om det er en Witch eller en Wizard der vinder
		// s� f�lgende m� ikke v�re tilladt:
		// Witch wit = GameUtil.fightMagical(jadis, merlin);
		// Wizard wiz = GameUtil.fightMagical(jadis, merlin);

		//		 bem�rk at fightMagical ikke b�r kuhne kaldes med Mortals
		// S�rg for at nedenst�ende kald ikke er lovligt
		Player p2 = GameUtil.fightMagical(x, svend);
		System.out.println("The winner says: " + mp.speak());

		System.out.println();

		// to slags Mortals kan blive til en Mortal eller en Player
		System.out.println("Y and Svend fight dirty:");
		Mortal mort = (Mortal) GameUtil.fightDirty(y, svend);
		Player play = GameUtil.fightDirty(y, svend);
		// men ikke en Magician eller en Thief eller en Warrior
		// Magician mag = GameUtil.fightDirty(y, svend);
		// Thief th = GameUtil.fightDirty(y, svend);
		// Warrior war = GameUtil.fightDirty(y, svend);
		System.out.println("The winner says: " + play.speak());

		System.out.println();

		// klassen Fight kan bruges til at fastholde historik p� kampene
		// En instans af Fight kan have referencer til de konkrete vindere,
		// til klasser, til enums - til hvad som helst
		System.out.println("Statestik over vindere:");
		GameUtil.printStatistics(fightparticipants);

		System.out.println();

		System.out.println("Statestik over vindertyper:");
		GameUtil.printStatistics(fighttypes);

		System.out.println();

		System.out.println("Visdom overvinder alt?");
		GameUtil.printStatistics(smartfight);

		System.out.println();

		System.out.println("En rodet liste der intet fort�ller os:");
		GameUtil.printStatistics(strongfight);
		// Se kommentarer i metoden printStatistics() for at se hvordan det
		// kunne g� s� galt..
	}
}
