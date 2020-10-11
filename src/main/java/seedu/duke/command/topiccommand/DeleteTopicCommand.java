package seedu.duke.command.topiccommand;

import seedu.duke.card.Subject;
import seedu.duke.card.SubjectList;
import seedu.duke.card.Topic;
import seedu.duke.card.TopicList;
import seedu.duke.ui.Ui;

public class DeleteTopicCommand extends TopicCommand {
    private String fullCommand;

    public DeleteTopicCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Deletes a Task in a <code>taskList</code>.
     *
     * @param subject the <code>Subject</code> instance of the Subject class for the user to delete from
     * @return
     */
    public Topic execute(Subject subject) throws NumberFormatException {
        String[] message = this.fullCommand.split(" ");
        TopicList topicList = subject.getTopics();
        int number = Integer.parseInt(message[1]);
        Topic topic = topicList.getList().get(number - 1);
        topicList.getList().remove(number - 1);
        Ui.printTopicDelete(topic, topicList.getList().size());
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
