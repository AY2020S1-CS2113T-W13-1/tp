package seedu.revised.command.flashcardcommand;

import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.ui.Ui;

public class SorryFlashcardCommand extends FlashcardCommand {

    public Flashcard execute(Topic topic) {
        Ui.printError();
        return null;
    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
