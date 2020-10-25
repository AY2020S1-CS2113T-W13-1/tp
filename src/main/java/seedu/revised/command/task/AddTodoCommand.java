package seedu.revised.command.task;


import seedu.revised.exception.task.TaskTodoException;
import seedu.revised.task.Task;
import seedu.revised.task.TaskList;
import seedu.revised.task.Todo;
import seedu.revised.ui.Ui;

public class AddTodoCommand extends TaskCommand {
    private String fullCommand;

    public AddTodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Adds an instance of the <code>Todo</code> class into a <code>TaskList</code>.
     *
     * @param taskList An instance of the <code>TaskList</code> class for the user to append to
     * @throws TaskTodoException If there are no parameters written to initialise the creation of a new Todo class
     */
    @Override
    public void execute(TaskList taskList) throws TaskTodoException {
        int startOfMessage = 5;
        int endOfMessage = fullCommand.length();
        if (endOfMessage <= startOfMessage) {
            throw new TaskTodoException(Ui.TODO_EXCEPTION);
        }
        String message = fullCommand.substring(startOfMessage, endOfMessage).strip();
        if (message.isEmpty()) {
            throw new TaskTodoException(Ui.TODO_EXCEPTION);
        } else {
            Task temp = new Todo(message, false);
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

