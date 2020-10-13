package seedu.revised.command.subjectcommand;

import seedu.revised.card.Subject;
import seedu.revised.exception.NoSubjectException;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.RepeatedSubjectException;

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
            throw new NoSubjectException();
        }
        String title = fullCommand.substring(startOfMessage,endOfMessage);
        if (title.isEmpty()) {
            throw new NoSubjectException();
        }
        assert title != null;
        for (Subject subject : subjectList.getList()) {
            if (subject.getTitle().equals(title)) {
                throw new RepeatedSubjectException();
            }
        }
        Subject temp = new Subject(title);
        subjectList.getList().add(temp);
        temp.printSubject(subjectList);
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
