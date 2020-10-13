package seedu.revised.card.quiz;

import seedu.revised.card.Flashcard;
import seedu.revised.exception.NoFlashCardException;
import seedu.revised.exception.NoTopicException;

import java.util.ArrayList;
import java.util.List;

public abstract class Quiz {
    protected Result result = new Result(0, 0);
    protected List<Flashcard> flashcards;
    protected List<String> incorrectAnswers = new ArrayList<>();

    public abstract void startQuiz(ResultList resultList) throws NoFlashCardException, NoTopicException;

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void checkAnswer(String answer, Flashcard flashcard) {

        if (flashcard.getAnswer().equalsIgnoreCase(answer)) {
            double updatedResult = this.result.getScore() + 1.0;
            this.result.updateResult(updatedResult);

        } else {
            this.incorrectAnswers.add(flashcard.getQuestion());
            this.incorrectAnswers.add(flashcard.getAnswer());
            this.incorrectAnswers.add(answer);
        }
    }

}
