package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.ui.Ui;

public class ResultSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public ResultSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;

    }

    public Subject execute(SubjectList subjectList) {

        String[] message = this.fullcommand.split(" ");
        Subject quizSubject = null;

        for (Subject subject : subjectList.getList()) {
            if (subject.toString().contains(message[1])) {
                quizSubject = subject;
            }
        }
        assert (quizSubject != null) : "No such subject exists!";
        Ui.printSubjectResults(quizSubject);
        return null;
    }
}
