package seedu.revised.command.task;

import seedu.revised.exception.FailedParseException;
import seedu.revised.ui.Ui;

public class SorryTaskCommand extends TaskCommand {

    public void execute() throws FailedParseException {
        throw new FailedParseException(Ui.FAILED_PARSE_EXCEPTION);
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
