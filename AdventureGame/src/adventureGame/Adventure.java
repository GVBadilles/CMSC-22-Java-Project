package adventureGame;

/**
 * 
 * Primary class that creates cave system.
 *
 */
public class Adventure {
	private Room entrance;
	
	/**
	 * Creates the rooms with description, along with
	 * the key, treasure, and trap door.
	 * 
	 * @return		the entrance of the cave system.
	 */
	public Room adventureCreate() {
		Room outside = new Room();
		
		outside.setDescription (
				"Standing outside, with a river nearby,\n"
				+ "a cave creaks open downwards (outside).");
		
		Room r1 = new Room();
		r1.setDescription (
				"Only a ray of light shines in the overhead, darkness seeping.\n"
				+ "A narrow, dim hall seen in the south (room1).");
		
		outside.setSide(5, r1);
		r1.setSide(4, outside);
		entrance = outside;
		
		Room r2 = new Room();
		r2.setDescription (
				"A seemingly empty room with dethered walls.\n"
				+ "A spot of light shines on the north.\n"
				+ "A broken wall with falling rocks to the south (room2).");
		
		Room r3 = new Room();
		r3.setDescription (
				"A flashlightt should be good here.\n"
				+ "A broken wall to the north.\n"
				+ "An opening can be seen southwards.\n"
				+ "A well with a ladder down sits in the middle (room3).");
		
		// Connects rooms 1, 2, 3, and outside.
		r1.setSide(1, r2);
		r2.setSide(0, r1);
		r2.setSide(1, r3);	
		r3.setSide(0, r2);
		
		Room r4 = new Room();
		r4.setDescription (
				"An opening can be seen northwards,\n"
				+ "another passage to the west.\n"
				+ "An open wardrobe with a hole sits in the east (room4)");
		
		Room r5 = new Room();
		r5.setDescription(
				"Going down the rusty ladder, the floor filled with moist dirt.\n"
				+ "Something flapping can be heard in the west (room5).");
		
		Room r6 = new Room();
		r6.setDescription(
				"Looking up, red eyes can be seen, turning out to be bats.\n"
				+ "Any more reason to stay? (room6)");
		
		Room r7 = new Room();
		r7.setDescription(
				"Entering the hole, droplets of water made the floor damp.\n"
				+ "You start to get irritated (room7).");
		
		// connects rooms 3, 4, 5, 6, and 7.
		r3.setSide(1, r4);
		r3.setSide(5, r5);
		r4.setSide(0, r3);
		r4.setSide(2, r7);
		r5.setSide(4, r3);
		r5.setSide(2, r6);
		r6.setSide(3, r5);
		r7.setSide(2, r4);
		
		Room r8 = new Room();
		r8.setDescription(
				"Something is slithering on the floor.\n"
				+ "A passage can be seen in the east.\n"
				+ "A narrow hallway runs in the west "
				+ "A dark room is almost visible in the south (room8).");
		
		Room r9 = new Room();
		r9.setDescription(
				"Just an empty room.\n"
				+ "There really isn't much to say (room9).");
		
		Room r10 = new Room();
		r10.setDescription(
				"Seems like something ravaged the area.\n"
				+ "Looking closer, a trap door is filled with debris.\n"
				+ "After clearing the rocks, the trap door is locked (room10).");
		
		Room r11 = new Room();
		r11.setDescription(
				"The room is very dark. Should have brought a flashlight.\n"
				+ "Why didn't you? (room11)");
		
		// Connects the remaining rooms.
		r4.setSide(3, r8);
		r8.setSide(2, r4);
		r8.setSide(3, r9);
		r8.setSide(1, r10);
		r9.setSide(2, r8);
		r10.setSide(0, r8);
		
		/* Creates the key to the trap door with description
		 * and insert item to room 6.
		 */
		Key theTrapKey = new Key();
		theTrapKey.setDescription("An old vintage key. Maybe it can open something?");
		r6.addItem(theTrapKey);
		
		/* 
		 * Creates the treasure with description
		 * and inserts it to the last room: room 11.
		 */
		 
		Treasure theTreasure = new Treasure();
		theTreasure.setDescription("A chest filled with gold coins and jewelries,");
		r11.addItem(theTreasure);
		
		// Add the trap door between room 10 and room 11.
		Door theTrapDoor = new Door(r10, r11, theTrapKey);
		r10.setSide(5, theTrapDoor);
		r11.setSide(4, theTrapDoor);
		
		// return the entrance
		entrance = outside;
		return entrance;
	}
}
