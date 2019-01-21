import java.io.*;
import java.util.*;

/**
 * This class defines a single room in the Adventure game. A room is
 * characterized by the following properties:
 * 
 * <ul>
 * <li>A room number, which must be greater than zero
 * <li>Its name, which is a one-line string identifying the room
 * <li>Its description, which is a multiline array describing the room
 * <li>A list of objects contained in the room
 * <li>A flag indicating whether the room has been visited
 * <li>A motion table specifying the exits and where they lead</li>
 * 
 * The external format of the room data file is described in the assignment
 * handout. The comments on the methods exported by this class show how to use
 * the initialized data structure.
 */

public class AdvRoom {
	private int number;
	private String name;
	private String[] description;
	private List<AdvObject> objects = new ArrayList<>();
	private boolean flag;
	private AdvMotionTableEntry[] motionTable;

	/**
	 * Returns the room number.
	 * 
	 * @usage int roomNumber = room.getRoomNumber();
	 * @return The room number
	 */
	public int getRoomNumber() {
		return number;
	}

	/**
	 * Returns the room name, which is its one-line description.
	 * 
	 * @usage String name = room.getName();
	 * @return The room name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns an array of strings that correspond to the long description of the
	 * room (including the list of the objects in the room).
	 * 
	 * @usage String[] description = room.getDescription();
	 * @return An array of strings giving the long description of the room
	 */
	public String[] getDescription() {
		return description;
	}

	/**
	 * Adds an object to the list of objects in the room.
	 * 
	 * @usage room.addObject(obj);
	 * @param The
	 *            AdvObject to be added
	 */
	public void addObject(AdvObject obj) {
		objects.add(obj);
	}

	/**
	 * Removes an object from the list of objects in the room.
	 * 
	 * @usage room.removeObject(obj);
	 * @param The
	 *            AdvObject to be removed
	 */
	public void removeObject(AdvObject obj) {
		objects.remove(obj);
	}

	/**
	 * Checks whether the specified object is in the room.
	 * 
	 * @usage if (room.containsObject(obj)) . . .
	 * @param The
	 *            AdvObject being tested
	 * @return true if the object is in the room, and false otherwise
	 */
	public boolean containsObject(AdvObject obj) {
		return objects.contains(obj);
	}

	/**
	 * Returns the number of objects in the room.
	 * 
	 * @usage int nObjects = room.getObjectCount();
	 * @return The number of objects in the room
	 */
	public int getObjectCount() {
		return objects.size();
	}

	/**
	 * Returns the specified element from the list of objects in the room.
	 * 
	 * @usage AdvObject obj = room.getObject(index);
	 * @return The AdvObject at the specified index position
	 */
	public AdvObject getObject(int index) {
		return objects.get(index);
		// AdvObject obj = objects.get(index);
		// removeObject(obj);
		// return obj;
	}

	/**
	 * Sets the flag indicating that this room has been visited according to the
	 * value of the parameter. Calling setVisited(true) means that the room has been
	 * visited; calling setVisited(false) restores its initial unvisited state.
	 * 
	 * @usage room.setVisited(flag);
	 * @param flag
	 *            The new state of the "visited" flag
	 */
	public void setVisited(boolean flag) {
		this.flag = flag;
	}

	/**
	 * Returns true if the room has previously been visited.
	 * 
	 * @usage if (room.hasBeenVisited()) . . .
	 * @return true if the room has been visited; false otherwise
	 */
	public boolean hasBeenVisited() {
		return flag;
	}

	/**
	 * Returns the motion table associated with this room, which is an array of
	 * directions, room numbers, and enabling objects stored in a
	 * AdvMotionTableEntry.
	 * 
	 * @usage AdvMotionTableEntry[] motionTable = room.getMotionTable();
	 * @return The array of motion table entries associated with this room
	 */
	public AdvMotionTableEntry[] getMotionTable() {
		return motionTable;
	}

	/**
	 * Reads the data for this room from the Scanner scan, which must have been
	 * opened by the caller. This method returns a room if the room initialization
	 * is successful; if there are no more rooms to read, readFromFile returns null.
	 * 
	 * @usage AdvRoom room = AdvRoom.readFromFile(scan);
	 * @param scan
	 *            A scanner open on the rooms data file
	 * @return a room if successfully read; null if at end of file
	 * @throws IOException
	 */
	public static AdvRoom readFromFile(Scanner sc) throws IOException {
		if (sc.hasNextLine()) {
			AdvRoom room = new AdvRoom();
			room.number = Integer.parseInt(sc.nextLine());
			room.name = sc.nextLine();
			String line;
			List<String> temp = new ArrayList<String>();
			while (!(line = sc.nextLine()).equals("-----")) {
				temp.add(line);
			}
			room.description = new String[temp.size()];
			temp.toArray(room.description);
			List<AdvMotionTableEntry> temp2 = new ArrayList<>();
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if (!(line.isEmpty())) {
					Scanner lineSc = new Scanner(line);
					String direction = lineSc.next();
					int destRoom;
					String key;
					if (lineSc.hasNextInt()) {
						destRoom = lineSc.nextInt();
						key = null;
					} else {
						String s = lineSc.next();
						String[] both = s.split("/");
						destRoom = Integer.parseInt(both[0]);
						key = both[1];
					}
					lineSc.close();
					temp2.add(new AdvMotionTableEntry(direction, destRoom, key));
				} else {
					break;
				}
			}
			room.motionTable = new AdvMotionTableEntry[temp2.size()];
			temp2.toArray(room.motionTable);
			room.setVisited(false);
			return room;
		}
		return null;
	}

	/**
	 * Prints room info
	 * 
	 */
	public void printRoom(boolean looking) {
		String roomDescription = "";
		String roomObjects = "";
		for (AdvObject obj : objects) {
			String n = obj.getName().toLowerCase();
			String p = (n.endsWith("s")) ? "are" : "is a";
			roomObjects += "There " + p + " " + n + " here.\n";
		}
		if (looking || !hasBeenVisited()) {
			for (String line : description) {
				roomDescription += line + "\n";
			}
		} else {
			roomDescription = name + "\n";
		}
		setVisited(true);
		if (!roomObjects.isEmpty()) {
			roomDescription += roomObjects;
		}
		System.out.print(roomDescription);
	}

}