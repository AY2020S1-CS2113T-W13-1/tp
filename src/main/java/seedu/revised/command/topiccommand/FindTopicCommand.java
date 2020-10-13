package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.card.TopicList;
import seedu.revised.command.taskcommand.FindTaskCommand;
import seedu.revised.ui.Ui;

public class FindTopicCommand extends TopicCommand {
    private String fullcommand;

    public FindTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Topic execute(Subject subject) {
        TopicList topicList = subject.getTopics();
        String[] message = this.fullcommand.split(" ");
        Ui.printFindTopic(topicList,message[1]);
        FindTaskCommand findTaskCommand = new FindTaskCommand(this.fullcommand);
        findTaskCommand.execute(subject.getTasks());
        return null;
    }

    /**
     * Checks whether the the user exits the program.
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
