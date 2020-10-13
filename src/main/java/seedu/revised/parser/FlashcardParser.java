package seedu.revised.parser;

import seedu.revised.command.flashcardcommand.AddFlashcardCommand;
import seedu.revised.command.flashcardcommand.ExitFlashcardCommand;
import seedu.revised.command.flashcardcommand.FlashcardCommand;
import seedu.revised.command.flashcardcommand.ListFlashcardCommand;
import seedu.revised.command.flashcardcommand.DeleteFlashcardCommand;
import seedu.revised.command.flashcardcommand.SorryFlashcardCommand;

public class FlashcardParser {

    /**
     * Parses the inputs provided by the user.
     *
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static FlashcardCommand parse(String fullCommand) {
        if (fullCommand.equals("exit")) {
            return new ExitFlashcardCommand();
        } else if (fullCommand.equals("list")) {
            return new ListFlashcardCommand();
        } else if (fullCommand.startsWith("add")) {
            return new AddFlashcardCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteFlashcardCommand(fullCommand);
        } else {
            return new SorryFlashcardCommand();
        }
    }
}
