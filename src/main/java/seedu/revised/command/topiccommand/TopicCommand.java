package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.Command;
import seedu.revised.exception.NoSubjectException;
import seedu.revised.exception.RepeatedSubjectException;

public class TopicCommand extends Command {
    public Topic execute(Subject subject) throws NoSubjectException, RepeatedSubjectException {
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
