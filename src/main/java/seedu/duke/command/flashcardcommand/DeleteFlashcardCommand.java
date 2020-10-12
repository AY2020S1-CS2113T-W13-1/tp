package seedu.duke.command.flashcardcommand;

import seedu.duke.card.Flashcard;
import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.card.TopicList;
import seedu.duke.ui.Ui;

public class DeleteFlashcardCommand extends FlashcardCommand {
	private String fullCommand;

	public DeleteFlashcardCommand(String fullCommand) {
		this.fullCommand = fullCommand;
	}

	/**
	 * Deletes a Flashcard in a <code>flashcardList</code>.
	 *
	 * @param topic the <code>Topic</code> instance of the Topic class for the user to delete from
	 * @return
	 */
	public Flashcard execute(Topic topic) throws NumberFormatException {
		String[] message = this.fullCommand.split(" ");
		int number = Integer.parseInt(message[1]);
		Flashcard flashcard = topic.getFlashcards().get(number - 1);
		topic.getFlashcards().remove(number - 1);
		Ui.printFlashcardDelete(flashcard, topic.getFlashcards().size());
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

