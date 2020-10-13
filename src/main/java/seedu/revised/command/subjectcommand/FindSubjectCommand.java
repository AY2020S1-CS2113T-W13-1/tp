package seedu.revised.command.subjectcommand;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.ui.Ui;

public class FindSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public FindSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Subject execute(SubjectList subjectList) {
        String[] message = this.fullcommand.split(" ");
        Ui.printFindSubject(subjectList,message[1]);
        return null;
    }

    /**
     * Checks whether the the user exits the program.
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
