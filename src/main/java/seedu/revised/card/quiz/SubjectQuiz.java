package seedu.revised.card.quiz;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SubjectQuiz extends Quiz {
    private static final Logger logger = Logger.getLogger(SubjectQuiz.class.getName());
    private Subject subject;

    public SubjectQuiz(Subject subject) {
        this.subject = subject;
        this.flashcards = new ArrayList<>();


    }

    /**
     * Transfers the flashcards from the all the topics in a subject to the SubjectQuiz class and
     * sets the maximum score of the quiz.
     *
     * @throws NoFlashcardException If the topic has no flashcards
     * @throws NoTopicException     If the subject has no topic
     */
    public void setUpQuiz() throws NoFlashcardException, NoTopicException {
        logger.info("Start setting up the quiz");
        if (subject.getTopics().getList().size() == 0) {
            throw new NoTopicException(Ui.NO_TOPIC_EXCEPTION);
        }
        int maxScore = 0;
        for (Topic topic : subject.getTopics().getList()) {
            this.flashcards.addAll(topic.getFlashcards());
            maxScore += topic.getFlashcards().size();

        }
        this.result.setMaxScore(maxScore);

        if (this.flashcards.size() == 0) {
            throw new NoFlashcardException(Ui.NO_FLASHCARD_EXCEPTION);
        }
        logger.info("Finished setting up the quiz");
        logger.fine(String.format("Max Score of quiz: %d", maxScore));
    }

    /**
     * Begins the quiz for the user.
     *
     * @param results The results of the subject
     * @throws NoFlashcardException If the topic has no flashcards
     * @throws NoTopicException     If the subject has no topic
     */
    public void startQuiz(ResultList results) throws NoFlashcardException, NoTopicException {
        logger.info("Start of the quiz");
        logger.fine(String.format("The subject being tested is  %s", this.subject));
        setUpQuiz();

        this.result.setScore(0);
        Ui.printStartSubjectQuiz(this.subject);

        Instant end = Instant.now().plusSeconds(120);
        String answer = null;

        logger.info("Start printing the questions");
        for (Flashcard flashcard : this.flashcards) {
            if (Instant.now().isAfter(end)) {
                logger.info("If the timer ends before the user could finish the quiz.");
                break;

            }
            Ui.printQuestion(flashcard.getQuestion());
            answer = Ui.readCommand().strip();
            if (answer.equals("stop")) {
                logger.info("If the user wants to end the quiz.");
                Ui.printStopQuiz();
                Ui.printScore(this.result);
                break;
            } else {
                checkAnswer(answer, flashcard);
            }
        }
        logger.fine(String.format("The last answer is : %s", answer));
        assert answer != null;
        if (!answer.equals("stop")) {
            Ui.printEndQuiz();
            Ui.printScore(this.result);
            if (this.result.getScore() < this.result.getMaxScore()) {
                Ui.printIncorrectAnswers(this.incorrectAnswers);
            }

        }
        this.subject.getResults().add(this.result);
        logger.info("End of quiz");
        logger.fine(String.format("The result for the quiz is %f",this.result.getScore()));

    }


}
