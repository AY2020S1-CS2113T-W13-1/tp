package seedu.revised.command.taskcommand;

import seedu.revised.task.TaskList;
import seedu.revised.ui.Ui;

/**
 * Allows marking a <code>Task</code> in a <code>TaskList</code> as done.
 */
public class DoneTaskCommand extends TaskCommand {
    private String fullCommand;

    public DoneTaskCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Marks a <code>Task</code> in a <code>TaskList</code> as done.
     *
     * @param taskList the <code>TaskList</code> instance of the TaskList class for the user to obtain the Task in the
     *                 TaskList specified by the user and set it as done
     */
    public void execute(TaskList taskList) throws NumberFormatException {
        String[] message = this.fullCommand.split(" ");
        int number = Integer.valueOf(message[1]);
        taskList.getList().get(number - 1).markAsDone();
        Ui.printDone(taskList.getList().get(number - 1));
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