package seedu.revised.card.quiz;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.exception.NoFlashCardException;
import seedu.revised.ui.Ui;

import java.time.Instant;

public class TopicQuiz extends Quiz {

    private Topic topic;

    public TopicQuiz(Topic topic) {
        this.topic = topic;
        this.flashcards = topic.getFlashcards();
        this.result.setMaxScore(this.flashcards.size());
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setUpQuiz() throws NoFlashCardException {
        int maxScore = 0;
        if (this.flashcards.size() == 0) {
            throw new NoFlashCardException();
        }
        this.result.setMaxScore(this.flashcards.size());

    }

    public void startQuiz(ResultList results) throws NoFlashCardException {
        setUpQuiz();
        this.result.setScore(0);

        Ui.printStartTopicQuiz(this.topic);

        Instant end = Instant.now().plusSeconds(60);
        String answer = null;

        for (Flashcard flashcard : this.flashcards) {
            if (Instant.now().isAfter(end)) {
                break;
            }
            Ui.printQuestion(flashcard.getQuestion());
            answer = Ui.readCommand().strip();
            if (answer.equals("stop")) {
                Ui.printStopQuiz();
                Ui.printScore(this.result);
                break;
            } else {
                checkAnswer(answer, flashcard);
            }

        }
        assert answer != null;


        if (!answer.equals("stop")) {
            Ui.printEndQuiz();
            Ui.printScore(this.result);
            if (this.result.getScore() < this.result.getMaxScore()) {
                Ui.printIncorrectAnswers(this.incorrectAnswers);
            }

        }


        this.topic.getResults().add(this.result);


    }


}
