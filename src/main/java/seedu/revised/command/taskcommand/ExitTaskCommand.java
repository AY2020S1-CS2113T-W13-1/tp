package seedu.revised.command.taskcommand;

import seedu.revised.task.TaskList;

public class ExitTaskCommand extends TaskCommand {

    public void execute(TaskList taskList) {
    }

    ;

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}