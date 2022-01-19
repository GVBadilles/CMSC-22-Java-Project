package adventureGame;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * 
 * class Room
 *
 */
public class Room implements CaveSystem {
	private String description;
	private CaveSystem[] side = new CaveSystem[6];
	private ArrayList<Item> contents = new ArrayList<Item>();
	
	Room() {
		side[0] = new Wall();
		side[1] = new Wall();
		side[2] = new Wall();
		side[3] = new Wall();
		side[4] = new Wall();
		side[5] = new Wall();
	}
	
	/**
	 * 
	 * @param d		sets the description
	 */
	public void setDescription(String d) {
		description = d;
	}
	
	/**
	 * 
	 * @param direction		gets the direction
	 * @param m				sets the direction
	 */
	public void setSide(int direction, CaveSystem m) {
		side[direction] = m;
	}
	
	/**
	 * 
	 * @param theItem	adds the item
	 */	
	public void addItem(Item theItem) {
		contents.add(theItem);
	}
	
	/**
	 * 
	 * @param theItem	removes/drops the item
	 */
	public void removeItem(Item theItem) {
		contents.remove(theItem);
	}
	
	/**
	 * 
	 * @return	the room to be empty
	 */
	public boolean roomEmpty() {
		return contents.isEmpty();
	}
	
	/**
	 * 
	 * @return	gets the items found in the room
	 */
	public Item[] getRoomContents() {
		Item[] contentsArray = new Item[contents.size()];
		contentsArray = contents.toArray(contentsArray);
		return contentsArray;
	}
	
	/**
	 * Sets the location of the player when entering
	 */
	public void enter(Player p) {
		p.setLocation(this);
	}
	
	/**
	 * 
	 * @param direction		where the player wants to go
	 * @param p				the player
	 */
	public void exit(int direction, Player p) {
		side[direction].enter(p);
	}
	
	/**
	 * 
	 * @return	the description of the room and items
	 */
	public String getDescription() {
		ListIterator<Item> roomContents = contents.listIterator();
		String contentString = "";
		while (roomContents.hasNext()) {
			contentString = contentString + (roomContents.next()).getDescription() + " ";
		}
		return description + '\n' + '\n' + "Room Contents: " + contentString + '\n';			
	}
}
