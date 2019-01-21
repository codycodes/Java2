

/**
 * This class is the general superclass for all commands in the Adventure game.
 * Every command has an execute method, which is called by the game when that
 * command is invoked. This method will be defined individually in each of the
 * subclasses.
 */

public abstract class AdvCommand {

	/** The predefined entry for the QUIT command */
	public static final AdvCommand QUIT = new QuitCommand();

	/** The predefined entry for the LOOK command */
	public static final AdvCommand LOOK = new LookCommand();

	/** The predefined entry for the INVENTORY command */
	public static final AdvCommand INVENTORY = new InventoryCommand();

	/** The predefined entry for the TAKE command */
	public static final AdvCommand TAKE = new TakeCommand();

	/** The predefined entry for the DROP command */
	public static final AdvCommand DROP = new DropCommand();

	/** The predefined entry for the HELP command */
	public static final HelpCommand HELP = new HelpCommand();

	/**
	 * Executes this command in the context of the specified adventure game. The
	 * execute method also takes a parameter obj, which specifies the object being
	 * used in the command. This value is typically null, but will be the
	 * appropriate object in the case of the TAKE and DROP commands.
	 * 
	 * @usage command.execute(game, obj);
	 * @param adv
	 *            The instance of the Adventure class that represents the game
	 * @param obj
	 *            The direct object (if any)
	 */
	public abstract void execute(Adventure game, AdvObject obj);

}

/**
 * This class implements the QUIT command.
 */
class QuitCommand extends AdvCommand {
	public void execute(Adventure game, AdvObject obj) {
		game.executeQuitCommand();
	}
}

/**
 * This class implements the LOOK command.
 */
class LookCommand extends AdvCommand {
	public void execute(Adventure game, AdvObject obj) {
		game.executeLookCommand();
	}
}

/**
 * This class implements the INVENTORY command.
 */
class InventoryCommand extends AdvCommand {
	public void execute(Adventure game, AdvObject obj) {
		game.executeInventoryCommand();
	}
}

/**
 * This class implements the TAKE command.
 */
class TakeCommand extends AdvCommand {
	public void execute(Adventure game, AdvObject obj) {
		game.executeTakeCommand(obj);
	}
}

/**
 * This class implements the DROP command.
 */
class DropCommand extends AdvCommand {
	public void execute(Adventure game, AdvObject obj) {
		game.executeDropCommand(obj);
	}
}

/**
 * This class implements the HELP command.
 */
class HelpCommand extends AdvCommand {
	public void execute(Adventure game, AdvObject obj) {
		game.executeHelpCommand();
	}
}