package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.ui.Ui;


/**
 * Adds an instance of the <code>Deadline</code> class into a <code>TaskList</code>.
 */
public class AddSubjectCommand extends SubjectCommand {
    private String fullCommand;

    public AddSubjectCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException, RepeatedSubjectException {
        int startOfMessage = 4;
        int endOfMessage = fullCommand.length();
        if (endOfMessage <= startOfMessage) {
            throw new NoSubjectException(Ui.NO_SUBJECT_EXCEPTION);
        }
        String title = fullCommand.substring(startOfMessage, endOfMessage).strip();
        if (title.isEmpty()) {
            throw new NoSubjectException(Ui.NO_SUBJECT_EXCEPTION);
        }
        assert title != null;
        for (Subject subject : subjectList.getList()) {
            if (subject.getTitle().equals(title)) {
                throw new RepeatedSubjectException(Ui.REPEATED_SUBJECT_EXCEPTION);
            }
        }
        Subject temp = new Subject(title);
        subjectList.getList().add(temp);
        Ui.printSubject(temp, subjectList);
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
