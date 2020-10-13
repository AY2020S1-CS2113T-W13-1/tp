package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.card.quiz.SubjectQuiz;
import seedu.duke.exception.NoSubjectException;

public class QuizSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public QuizSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public String getFullcommand() {
        return this.fullcommand;
    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException {
        String[] message = this.fullcommand.split(" ");
        Subject quizSubject = null;
        for (Subject subject : subjectList.getList()) {
            if (subject.toString().contains(message[1])) {
                quizSubject = subject;
            }
        }
        if (quizSubject == null) {
            throw new NoSubjectException();
        }
        return quizSubject;

    }

    public boolean isExit() {
        return false;
    }
}
