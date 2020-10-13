package seedu.revised.command.taskcommand;

import seedu.revised.ui.Ui;

public class SorryTaskCommand extends TaskCommand {

    public void execute() {
        Ui.printError();
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
