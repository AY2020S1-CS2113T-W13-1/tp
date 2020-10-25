package seedu.revised.command.flashcard;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.exception.flashcard.RepeatedFlashcardException;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.ui.Ui;


public class AddFlashcardCommand extends FlashcardCommand {
    private final String fullCommand;

    public AddFlashcardCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public Flashcard execute(Topic topic) throws NoFlashcardException, RepeatedFlashcardException {
        int startOfMessage = 4;
        int endOfQuestion = (fullCommand.indexOf(";"));
        int startOfAnswer = endOfQuestion + 2;
        int endOfMessage = fullCommand.length();
        if (endOfMessage <= startOfMessage) {
            throw new NoFlashcardException(Ui.NO_FLASHCARD_EXCEPTION);
        }
        String question = fullCommand.substring(startOfMessage, endOfQuestion);
        String answer = fullCommand.substring(startOfAnswer, endOfMessage);
        if (question.isEmpty() || answer.isEmpty()) {
            throw new NoFlashcardException(Ui.NO_FLASHCARD_EXCEPTION);
        }
        for (Flashcard flashcard : topic.getFlashcards()) {
            if (flashcard.getQuestion().equals(question) && flashcard.getAnswer().equals(answer)) {
                throw new RepeatedFlashcardException(Ui.REPEATED_FLASHCARD_EXCEPTION);
            }
        }
        Flashcard t = new Flashcard(question, answer);
        topic.addFlashcard(t);
        Ui.printFlashcard(t, topic.getFlashcards());
        return t;
    }

    public boolean isExit() {
        return false;
    }
}
