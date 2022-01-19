package adventureGame;

/**
 * 
 * class Door
 *
 */

public class Door implements CaveSystem {
	/**
	 * The doors are locked and and needs a key to enter the room.
	 * The doors then slams shut and locks itself after the player
	 * enters the room.
	 */
	private Key myKey;
	
	/**
	 * Initialize the location of the door
	 */
	private CaveSystem outArea;
	private CaveSystem inArea;
	
	/**
	 * Construct the door in the area.
	 * 
	 * @param out	outside area
	 * @param in	inside area
	 * @param k		the needed key
	 */
	Door(CaveSystem out, CaveSystem in, Key k) {
		outArea = out;
		inArea = in;
		myKey = k;
	}
	
	/**
	 * In order to enter, the player needs the key
	 */
	public void enter(Player p) {
		/*
		 * If the player have the key, the player enters
		 * and the door shuts and locks itself.
		 */
		if (p.haveItem(myKey)) {
			System.out.println("The key fits! Good thing you kept it.");
			System.out.println("The door slams behind you after you entered it.");
			if (p.getLocation() == outArea) inArea.enter(p);
			else if (p.getLocation() == inArea) outArea.enter(p);
		}
		
		// No key can be found in the inventory.
		else {
			System.out.println("You don't have the key, smashing it won't work.");
			System.out.println("Better find it.");
		}
	}
}
