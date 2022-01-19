package adventureGame;

/**
 * 
 * class Wall
 *
 */
public class Wall implements CaveSystem {
	
	/**
	 * Displays when the player hits a wall.
	 */
	public void enter(Player p) {
		System.out.println("Get bonked by the wall.\n");
	}
}
