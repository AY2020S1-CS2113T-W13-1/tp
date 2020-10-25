package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ResultSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(ResultSubjectCommand.class.getName());
    private String fullcommand;

    public ResultSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;

    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException, InvalidSubjectException {
        logger.info("Begin finding the subject for which the results feature has to be called.");
        String[] message = this.fullcommand.split(" ");
        if (message.length == 1) {
            throw new InvalidSubjectException(Ui.INVALID_SUBJECT_EXCEPTION);
        }
        Subject resultSubject = null;

        for (Subject subject : subjectList.getList()) {
            if (subject.toString().contains(message[1])) {
                resultSubject = subject;
            }
        }
        if (resultSubject == null) {
            throw new NoSubjectException(Ui.NO_SUBJECT_EXCEPTION);
        }
        assert (resultSubject != null) : "No such subject exists!";
        Ui.printSubjectResults(resultSubject);
        logger.info("Finish reading the command to find the subject for the results feature.Now, the "
                + "application prints the results.");
        logger.fine(String.format("The subject is %s", resultSubject.getTitle()));
        return null;
    }
}
