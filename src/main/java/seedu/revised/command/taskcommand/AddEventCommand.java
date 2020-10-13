package seedu.revised.command.taskcommand;

import seedu.revised.exception.TaskEventException;
import seedu.revised.task.Event;
import seedu.revised.task.TaskList;

public class AddEventCommand extends TaskCommand {
    private String fullCommand;

    public AddEventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }


    /**
     * Adds an instance of an <code>Event</code> class into a <code>TaskList</code>.
     *
     * @param taskList An instance of the <code>TaskList</code> class for the user to append to
     * @throws TaskEventException If there are no parameters written to initialise the creation of a new Event class
     */
    public void execute(TaskList taskList) throws TaskEventException {
        int startOfMessage = 6;
        int endOfMessage = fullCommand.indexOf("/at") - 1;
        int startOfAt = fullCommand.indexOf("/at") + 4;
        int endOfAt = fullCommand.length();
        if (endOfMessage <= startOfMessage) {
            throw new TaskEventException();
        }
        String message = fullCommand.substring(startOfMessage, endOfMessage);
        String at = fullCommand.substring(startOfAt, endOfAt);
        if (message.isEmpty() || at.isEmpty()) {
            throw new TaskEventException();
        }
        Event temp = new Event(message, false, at);
        taskList.getList().add(temp);
        temp.printEvent(taskList);
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
