package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.quiz.SubjectQuiz;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.list.SubjectList;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class QuizSubjectCommand extends SubjectCommand {
    private static final Logger logger = Logger.getLogger(QuizSubjectCommand.class.getName());
    private String fullcommand;

    public QuizSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public String getFullcommand() {
        return this.fullcommand;
    }

    /**
     * Quiz subjects in a <code>SubjectList</code>.
     *
     * @param subjectList An instance of the <code>SubjectList</code> class for the user to quiz on
     * @param storage     Does nothing in this case but needed since this method was implemented
     *                    from an abstract class
     */
    public void execute(SubjectList subjectList, Storage storage) throws NoSubjectException, InvalidSubjectException,
            NoTopicException, NoFlashcardException {
        logger.info("Begin finding the subject for which the quiz has to be conducted.");
        String[] message = this.fullcommand.split(" ");
        Subject quizSubject = null;
        if (message.length == 1) {
            throw new InvalidSubjectException(Ui.INVALID_SUBJECT_EXCEPTION);
        }
        for (Subject subject : subjectList.getList()) {
            if (subject.toString().contains(message[1])) {
                quizSubject = subject;
            }
        }
        if (quizSubject == null) {
            throw new NoSubjectException(Ui.NO_SUBJECT_EXCEPTION);
        }
        logger.info("Finish reading the command to find the subject for the quiz feature");
        logger.fine(String.format("The subject is %s", quizSubject.getTitle()));

        startQuiz(quizSubject);
    }

    /**
     * Starts the quiz of the subject specified in the param.
     *
     * @param subject               An instance of the <code>Subject</code> class for the user to quiz on
     * @throws NoTopicException     If there are no instances of <code>Topic</code> available for the
     *                              program to quiz from
     * @throws NoFlashcardException If there are no instances of <code>Flashcard</code> available for the
     *                              program to quiz from
     */
    private void startQuiz(Subject subject) throws NoTopicException, NoFlashcardException {
        SubjectQuiz subjectQuiz = new SubjectQuiz(subject);
        subjectQuiz.startQuiz();
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> If user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
