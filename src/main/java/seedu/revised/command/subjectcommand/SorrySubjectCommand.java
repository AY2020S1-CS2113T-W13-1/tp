package seedu.revised.command.subjectcommand;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.ui.Ui;

public class SorrySubjectCommand extends SubjectCommand {

    public Subject execute(SubjectList subjectList) {
        Ui.printError();
        return null;
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
