package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.flashcard.FlashcardCommand;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.flashcard.RepeatedFlashcardException;
import seedu.revised.exception.flashcard.RepeatedFlashcardException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.parser.FlashcardParser;
import seedu.revised.task.TaskList;
import seedu.revised.ui.Ui;

public class ReturnTopicCommand extends TopicCommand {
    private String fullcommand;
    private TaskList tasks;

    public ReturnTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Topic execute(Subject subject) throws NoTopicException {
        String[] message = this.fullcommand.split(" ");
        if (message[1].isEmpty()) {
            throw new NoTopicException(Ui.TOPIC_NOT_FOUND_EXCEPTION);
        }
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.getTitle().equals(message[1])) {
                return topic;
            }
        }
        throw new NoTopicException(Ui.TOPIC_NOT_FOUND_EXCEPTION);
    }

    public void goToTopic(Topic topic, Subject subject) {
        Ui.printGoToTopic(topic);
        boolean isTopicExit = false;
        while (!isTopicExit) {
            try {
                String fullCommand = Ui.readCommand();
                FlashcardCommand c = FlashcardParser.parse(fullCommand);
                isTopicExit = c.isExit();
                c.execute(topic);
            } catch (NumberFormatException e) {
                Ui.printErrorMsg(Ui.INDEX_FORMAT_EXCEPTION);
            } catch (IndexOutOfBoundsException e) {
                Ui.printErrorMsg(Ui.INDEX_OUT_OF_BOUND_EXCEPTION);
            } catch (Exception e) {
                Ui.printError(e);
            }
        }
        Ui.printBackToTopicsAndTasks();
    }

    public boolean isExit() {
        return false;
    }
}
