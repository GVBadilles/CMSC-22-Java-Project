package adventureGame;

/**
 * 
 * class Player
 *
 */

public class Player {
	private Room myLocation;
	private Item[] myItems = new Item[2];
	private int itemCount = 0;
	
	/**
	 * 
	 * @param r	sets the location of the player
	 * 			to the room
	 */
	public void setRoom(Room r) {
		myLocation = r;
	}
	
	/**
	 * 
	 * @return	gets the description of the room/location
	 */
	public String look() {
		return myLocation.getDescription();
	}
	 
	/**
	 * 
	 * @param direction		walks to the direction
	 */
	public void go(int direction) {
		myLocation.exit(direction, this);
	}
	
	/**
	 * pickUp method gets the item in the room,
	 * increments the inventory,
	 * and remove the item from the room.
	 * 
	 * @param i		item in the room
	 */
	
	public void pickUp(Item i) {
		if (itemCount < 2) {
			myItems[itemCount] = i;
			itemCount++;
			myLocation.removeItem(i);
		}
	}
	
	/**
	 * haveItem method returns a boolean if the item
	 * is currently held by the user.
	 * 
	 * @param itemToFind	item the user wants to see
	 * @return				true if the item is present, false otherwise
	 */
	public boolean haveItem(Item itemToFind) {
		for (int n = 0; n < itemCount; n++) 
			if (myItems[n] == itemToFind)
				return true;
			return false;
	}
	
	/**
	 * dropItem method releases the item
	 * from the user's inventory
	 * 
	 * @param itemNum		gets the item
	 */
	public void dropItem(int itemNum) {
		if (itemNum > 0 & itemNum <= itemCount) {
			switch(itemNum) {
			case 1:	{
				myLocation.addItem(myItems[0]);
				myItems[0] = myItems[1];
				itemCount--;
				break;
				}
			case 2: {
				myLocation.addItem(myItems[1]);
				itemCount--;
				break;
				}
			default:
				System.out.println("Invalid input.\n");
			}
		}
	}
	
	/**
	 * 
	 * @param r  sets the room to be the location of the user
	 */
	public void setLocation(Room r) {
		myLocation = r;
	}
	
	/**
	 * 
	 * @return	the location of the user
	 */
	public Room getLocation() {
		return myLocation;
	}
	
	/**
	 * 
	 * @return	the items the user currently have
	 */
	public String showMyItems() {
		String outStr = "";
		for (int n = 0; n < itemCount; n++) 
			outStr = outStr + Integer.toString(n+1) + ": " + myItems[n].getDescription() + "\n";
		return outStr;
	}
	
	
	/**
	 * 
	 * @return	itemCount if both hands have items
	 */
	public boolean handsFull() {
		return itemCount == 2;
	}
	
	/**
	 * 
	 * @return	itemCount if the player doesn't have
	 * 			any items
	 */
	public boolean handsEmpty() {
		return itemCount == 0;
	}
	
	/**
	 * 	
	 * @return	gets the number of items the player have
	 */
	public int numItemsCarry() {
		return itemCount;
	}
}
