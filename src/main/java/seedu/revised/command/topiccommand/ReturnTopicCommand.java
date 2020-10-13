package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.flashcardcommand.FlashcardCommand;
import seedu.revised.exception.NoSubjectException;
import seedu.revised.exception.RepeatedSubjectException;
import seedu.revised.parser.FlashcardParser;
import seedu.revised.task.TaskList;
import seedu.revised.ui.Ui;

public class ReturnTopicCommand extends TopicCommand {
    private String fullcommand;
    private TaskList tasks;

    public ReturnTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Topic execute(Subject subject) throws NoSubjectException {
        String[] message = this.fullcommand.split(" ");
        //Subject currentSubject = new Subject(message[1]);
        if (message[1].isEmpty()) {
            throw new NoSubjectException();
        }
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.getTitle().equals(message[1])) {
                return topic;
            }
        }
        throw new NoSubjectException();
    }

    public void goToTopic(Topic topic, Subject subject) {
        Ui.printGoToTopic(topic);
        boolean isTopicExit = false;
        while (!isTopicExit) {
            String fullCommand = Ui.readCommand();
            FlashcardCommand c = FlashcardParser.parse(fullCommand);
            isTopicExit = c.isExit();
            try {
                c.execute(topic);
            }  catch (NumberFormatException e) {
                Ui.printIndexError();
            } catch (NoSubjectException e) {
                Ui.printNoTopicError();
            } catch (RepeatedSubjectException e) {
                Ui.printRepeatedTopicError();
            } catch (IndexOutOfBoundsException e) {
                Ui.printOutOfBoundsError();
            }
        }
        Ui.printBackToTopicsAndTasks();
    }

    public boolean isExit() {
        return false;
    }
}
