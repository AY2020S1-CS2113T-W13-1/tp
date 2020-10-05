package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;

public class ExitSubjectCommand extends SubjectCommand {

    public Subject execute(SubjectList subjectList) {
        return null;
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return true;
    }
}
