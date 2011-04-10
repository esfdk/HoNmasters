package bosk.ovelse10.ovelse105;

import java.util.Collection;

public class GameUtil {

	public static <N extends Player> N fightSmart(N one, N two){
		int i = one.getSmartness().compareTo(two.getSmartness());
		return findWinner(one, two, i);
	}

	public static <N extends Player> N fightDirty(N one, N two){
		int i = one.getStrength().compareTo(two.getStrength());
		return findWinner(one, two, i);
	}

	public static <N extends Player> N fightMagical(N one, N two){
		Magician m1 = (Magician) one;
		Magician m2 = (Magician) two;
		int i = m1.getMagicPowers().compareTo(m2.getMagicPowers());
		return findWinner(one, two, i);
	}

	private static <N extends Player> N findWinner(N one, N two, int i){
		if (i>0){
			update(one, two, one);
			return one;
		}
		else if (i < 0){
			update(one, two, two);
			return two;
		}
		else{
			if (Math.random() > 0.5){
				update(one, two, one);
				return one;
			}
			else{
				update(one, two, two);
				return two;
			}
		}
	}


	private static void update(Player one, Player two, Player winner){
		// Vi kan lave alle mulige slags Fight-instanser:

		// listen fightparticipants gemmer historik over hvilke spillere der har k�mpet mod hinanden
		GamePlay.fightparticipants.add(new Fight(one, two, winner));

		// listen fighttypes gemmer historik over hvilke typer af spillere (Thief, Wizard, etc) der har k�mpet mod hinanden. Bem�rk
		// metoden getClass().getSimpleName() giver os navnet p� den klasse som objektet er instantieret af
		GamePlay.fighttypes.add(new Fight(one.getClass().getSimpleName(), two.getClass().getSimpleName(), winner.getClass().getSimpleName()));

		// listen smartfight gemmer historik over hvordan det g�r to k�mpende med hver deres intelligensniveau. Kan bruges til at
		// regulere i spillet s� vi ikke f�r for mange stupide vindere :-)
		GamePlay.smartfight.add(new Fight(one.smartness, two.smartness, winner.smartness));

		// Hvis Fight parameteriseres kan man ikke inds�tte forkert
		// GamePlay.smartfight.add(new Fight<Smartness>(one.strength, two.speak(), winner.getClass())); er ikke lovligt

		// men uden parameterisering g�r det fint med at lave rod. Og vi f�r lov at s�tte instansen ind i vores
		// "typesikre" Liste i GamePlay! S� warnings i forbindelse m generics skal tages seri�st..
		// Husk parameterisering af Collections er kun g�ldende p� compiletime. Runtime glemmes typerne igen

		// GamePlay.strongfight.add(new Fight(one.strength, two.speak(), winner.getClass())); kan godt lade sig g�re
	}

	public static void printAllPlayers(Collection players){
		for(Object p:players)
			System.out.println(p);
	}

	public static void printStatistics(Collection fights){
		for(Object f:fights)
			System.out.println(f);
	}
}
