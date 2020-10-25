package seedu.revised.command.task;

import seedu.revised.exception.task.RepeatedDateTimeException;
import seedu.revised.exception.task.TaskDeadlineException;
import seedu.revised.task.Deadline;
import seedu.revised.task.Task;
import seedu.revised.task.TaskList;
import seedu.revised.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     *                               class
     */
    public void execute(TaskList taskList) throws TaskDeadlineException, RepeatedDateTimeException {
        int startOfMessage = 9;
        int endOfMessage = fullCommand.indexOf("/by") - 1;
        int startOfBy = fullCommand.indexOf("/by") + 4;
        int endOfBy = fullCommand.length();
        if (endOfMessage <= startOfMessage) {
            throw new TaskDeadlineException(Ui.DEADLINE_EXCEPTION);
        }
        String message = fullCommand.substring(startOfMessage, endOfMessage).strip();
        String by = fullCommand.substring(startOfBy, endOfBy);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm d-MM-yyyy");
        LocalDateTime dateTime = LocalDateTime.parse(by, format);
        if (message.isEmpty() || by.isEmpty()) {
            throw new TaskDeadlineException(Ui.DEADLINE_EXCEPTION);
        } else {
            Task temp = new Deadline(message, false, dateTime);
            for (Task task : taskList.getList()) {
                if (task.getDateTime().equals(temp.getDateTime())) {
                    throw new RepeatedDateTimeException(Ui.repeatedDateTimeException(task));
                }
            }
            taskList.getList().add(temp);
            Ui.printTask(temp, taskList);
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
