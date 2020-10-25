package seedu.revised.command.task;

import seedu.revised.exception.task.RepeatedDateTimeException;
import seedu.revised.exception.task.TaskEventException;
import seedu.revised.task.Event;
import seedu.revised.task.Task;
import seedu.revised.task.TaskList;
import seedu.revised.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public void execute(TaskList taskList) throws TaskEventException, RepeatedDateTimeException {
        int startOfMessage = 6;
        int endOfMessage = fullCommand.indexOf("/at") - 1;
        int startOfAt = fullCommand.indexOf("/at") + 4;
        int endOfAt = fullCommand.length();
        if (endOfMessage <= startOfMessage) {
            throw new TaskEventException(Ui.EVENT_EXCEPTION);
        }
        String message = fullCommand.substring(startOfMessage, endOfMessage).strip();
        String at = fullCommand.substring(startOfAt, endOfAt);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm d-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(at, format);


        if (message.isEmpty() || at.isEmpty()) {
            throw new TaskEventException(Ui.EVENT_EXCEPTION);
        }
        Task temp = new Event(message, false, dateTime);
        for (Task task : taskList.getList()) {
            if (task.getDateTime().equals(temp.getDateTime())) {
                throw new RepeatedDateTimeException(Ui.repeatedDateTimeException(task));
            }
        }
        taskList.getList().add(temp);
        Ui.printTask(temp, taskList);
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
