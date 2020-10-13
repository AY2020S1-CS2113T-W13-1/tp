package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.ui.Ui;

public class ListTopicCommand extends TopicCommand {

    public Topic execute(Subject subject) {
        Ui.printTopicList(subject);
        Ui.printTaskList(subject);
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
