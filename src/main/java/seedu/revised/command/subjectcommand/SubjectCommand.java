package seedu.revised.command.subjectcommand;

import seedu.revised.card.Subject;
import seedu.revised.command.Command;
import seedu.revised.exception.NoSubjectException;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.RepeatedSubjectException;

public class SubjectCommand extends Command {
    public Subject execute(SubjectList subjectList) throws NoSubjectException, RepeatedSubjectException {
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
