package seedu.revised.command.taskcommand;

import seedu.revised.card.Subject;
import seedu.revised.ui.Ui;

/**
 * A class that allows the user to list down the tasks they input into the <code>TaskList</code>.
 */
public class ListTaskCommand extends TaskCommand {

    public void execute(Subject subject) {
        Ui.printTaskList(subject);
    }

    public boolean isExit() {
        return false;
    }

}
