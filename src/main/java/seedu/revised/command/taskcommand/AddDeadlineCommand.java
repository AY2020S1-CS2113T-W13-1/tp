package seedu.revised.command.taskcommand;

import seedu.revised.exception.TaskDeadlineException;
import seedu.revised.task.Deadline;
import seedu.revised.task.TaskList;

public class AddDeadlineCommand extends TaskCommand {
    private String fullCommand;

    public AddDeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Adds an instance of the <code>Deadline</code> class into a <code>TaskList</code>.
     *
     * @param taskList An instance of the <code>TaskList</code> class for the user to append to
     * @throws TaskDeadlineException If there are no parameters written to initialise the creation of a new Deadline
     *      class
     */
    public void execute(TaskList taskList) throws TaskDeadlineException {
        int startOfMessage = 9;
        int endOfMessage = fullCommand.indexOf("/by") - 1;
        int startOfBy = fullCommand.indexOf("/by") + 4;
        int endOfBy = fullCommand.length();
        if (endOfMessage <= startOfMessage) {
            throw new TaskDeadlineException();
        }
        String message = fullCommand.substring(startOfMessage, endOfMessage);
        String by = fullCommand.substring(startOfBy, endOfBy);
        if (message.isEmpty() || by.isEmpty()) {
            throw new TaskDeadlineException();
        } else {
            Deadline temp = new Deadline(message, false, by);
            taskList.getList().add(temp);
            temp.printDeadline(taskList);
        }
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
