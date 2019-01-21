
import java.io.*;
import java.util.*;

/**
 * This class is the main program class for the Adventure game.
 */

public class Adventure {

	// Use this scanner for any console input
	private static Scanner scan = new Scanner(System.in);
	private Map<Integer, AdvRoom> roomMap = new TreeMap<>();
	private Map<String, AdvObject> objectMap = new HashMap<>();
	private Map<String, String> synonymMap = new HashMap<>();
	private List<AdvObject> inventory = new ArrayList<>();
	private AdvRoom current;
	private boolean quit = false;

	/**
	 * This method is used only to test the program
	 */
	public static void setScanner(Scanner theScanner) {
		scan = theScanner;
	}

	/**
	 * Runs the adventure program
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		start();
	}
	/**
	 * Starts adventure game, also used for restarting
	 * 
	 * @throws IOException
	 *
	 */
	public static void start() throws IOException {
		Adventure adventure = new Adventure();
		adventure.setUpFiles();
		adventure.enterRoom(1);
	}

	/**
	 * Gets info from files
	 * 
	 * @throws IOException
	 */
	public void setUpFiles() throws IOException {
		System.out.println("Where are your adventure files? \n");
		String name = scan.nextLine();
		File fileObjects = new File(name + "Objects.txt");
		File fileRooms = new File(name + "Rooms.txt");
		File fileSynonyms = new File(name + "Synonyms.txt");
		createObjects(fileObjects); // BEFORE ROOMS
		createRooms(fileRooms);
		createSynonyms(fileSynonyms);
	}

	/**
	 * Initializes the synonym map
	 * 
	 * @param fileSynonyms
	 * @throws IOException
	 */
	public void createSynonyms(File fileSynonyms) throws IOException {
		Scanner synScan = new Scanner(fileSynonyms);
		while (synScan.hasNextLine()) {
			String line = synScan.nextLine();
			String[] both = line.split("=");
			synonymMap.put(both[0], both[1]);
		}
		synScan.close();
	}

	/**
	 * Initializes room map
	 * 
	 * @param fileRooms
	 *            The rooms text file
	 * @throws IOException
	 */
	public void createRooms(File fileRooms) throws IOException {
		Scanner roomScan = new Scanner(fileRooms);
		AdvRoom room;
		while ((room = AdvRoom.readFromFile(roomScan)) != null) {
			int key = room.getRoomNumber();
			roomMap.put(key, room);
			for (AdvObject obj : objectMap.values()) {
				if (obj.getInitialLocation() == key) {
					room.addObject(obj);
				}
			}
		}
	}

	/**
	 * Initializes the object map
	 * 
	 * @param fileObjects
	 * @throws IOException
	 */
	public void createObjects(File fileObjects) throws IOException {
		Scanner objScan = new Scanner(fileObjects);
		AdvObject obj;
		while ((obj = AdvObject.readFromFile(objScan)) != null) {
			String key = obj.getName();
			objectMap.put(key, obj);
		}
	}

	/**
	 * Displays room when entered
	 * 
	 * @param i
	 *            The room number
	 * 
	 */
	public void enterRoom(int i) {
		if (i != 0) {
			current = roomMap.get(i);
			current.printRoom(false);
			AdvMotionTableEntry entry = current.getMotionTable()[0];
			if (entry.getDirection().equals("FORCED")) {
				enterRoom(entry.getDestinationRoom());
			} else {
				getCommand();
			}
		}
	}

	/**
	 * Gets commands from user
	 */
	public void getCommand() {
		AdvCommand cmd = null;
		System.out.print(">");
		String input;
		if (scan.hasNextLine()) {
			input = scan.nextLine().toUpperCase();
		} else {
			return;
		}
		String obj = null;
		if (input.contains(" ")) {
			String[] split = input.split(" ");
			input = split[0];
			obj = split[1];
		}
		if (synonymMap.containsKey(input)) {
			input = synonymMap.get(input);
		}
		AdvObject object = null;
		if (objectMap.containsKey(obj)) {
			object = objectMap.get(obj);
		}
		switch (input) {
		case "TAKE":
			cmd = AdvCommand.TAKE;
			break;
		case "DROP":
			cmd = AdvCommand.DROP;
			break;
		case "LOOK":
			cmd = AdvCommand.LOOK;
			break;
		case "INVENTORY":
			cmd = AdvCommand.INVENTORY;
			break;
		case "HELP":
			cmd = AdvCommand.HELP;
			break;
		case "QUIT":
			cmd = AdvCommand.QUIT;
			break;
		default:
			// any direction command
			cmd = new AdvMotionCommand(input);
		}
		if (cmd != null) {
			cmd.execute(this, object);
		}
		if (!quit) {
			getCommand();
		}
	}

	/**
	 * Executes a motion command. This method is called from the AdvMotionCommand
	 * class to move to a new room.
	 * 
	 * @param direction
	 *            The string indicating the direction of motion
	 */
	public void executeMotionCommand(String direction) {
		int count = 0;
		for (AdvMotionTableEntry entry : current.getMotionTable()) {
			count++;
			if (direction.equals(entry.getDirection())) {
				if (entry.getKeyName() != null && !inventory.isEmpty()) {
					for (AdvObject obj : inventory) {
						
						if (obj.getName().equals(entry.getKeyName())) {
							enterRoom(entry.getDestinationRoom());
							return;
						}
					}
				} else {
					enterRoom(entry.getDestinationRoom());
				}
			}
		}
		if(current.getMotionTable().length == count) {
			System.out.println("The command you entered isn't valid. Please try another!");
		}
	}

	/**
	 * Implements the QUIT command. This command should ask the user to confirm the
	 * quit request and, if so, should exit from the play method. If not, the
	 * program should continue as usual.
	 * 
	 * @throws IOException
	 */
	public void executeQuitCommand() {
		System.out.print("Do you want to quit? Hit 'y' to quit... ");
		if (scan.next().toLowerCase().startsWith("y")) {
			System.out.println("You quit the game!");
			quit = true;
		}
	}

	/**
	 * Implements the HELP command. Your code must include some help text for the
	 * user.
	 */
	public void executeHelpCommand() {

		System.out.println(
				"To move, try words like directions (north, south, east, west), or words like inside, outside, up, down, etc.\n"

						+ "You can also use synonyms, such as N, S, E, W, U, D\n"

						+ "To get a long description of where you're located, use the type \"look\" or 'l' \n"

						+ "To take an item, you can use the command \"take\", followed by name of the item (e.g. \"take bottle\"\n"

						+ "Substitute the word drop instead of take for dropping an item\n"

						+ "You can quit the game by using the word \"quit\" or 'q'");

	}

	/**
	 * Implements the LOOK command. This method should give the full description of
	 * the room and its contents.
	 */
	public void executeLookCommand() {
		current.printRoom(true);
	}

	/**
	 * Implements the INVENTORY command. This method should display a list of what
	 * the user is carrying.
	 */
	public void executeInventoryCommand() {
		if (inventory.isEmpty()) {
			System.out.println("Inventory is empty");
		} else {
			for (AdvObject obj : inventory) {
				System.out.println(obj.getName());
			}
		}
	}

	/**
	 * Implements the TAKE command. This method should check that the object is in
	 * the room and deliver a suitable message if not.
	 * 
	 * @param obj
	 *            The AdvObject you want to take
	 */
	public void executeTakeCommand(AdvObject obj) {
		if (current.getObjectCount() == 0 || !current.containsObject(obj)) {
			System.out.println("Object is not in this room");
		} else {
			inventory.add(obj);
			current.removeObject(obj);
		}
	}

	/**
	 * Implements the DROP command. This method should check that the user is
	 * carrying the object and deliver a suitable message if not.
	 * 
	 * @param obj
	 *            The AdvObject you want to drop
	 */
	public void executeDropCommand(AdvObject obj) {
		if (!inventory.isEmpty() && inventory.contains(obj)) {
			current.addObject(obj);
			inventory.remove(obj);
		} else {
			System.out.println("Object is not in inventory");
		}
	}

}
