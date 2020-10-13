package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.exception.NoSubjectException;
import seedu.revised.exception.RepeatedSubjectException;

public class FlashcardCommand {
    public Flashcard execute(Topic topic) throws NoSubjectException, RepeatedSubjectException {
        return null;
    }

    public boolean isExit() {
        return false;
    }
}