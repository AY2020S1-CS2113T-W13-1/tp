package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.ui.Ui;

public class ListFlashcardCommand extends FlashcardCommand {
    public Flashcard execute(Topic topic) {
        Ui.printFlashcardList(topic);
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
