package seedu.revised.command.topiccommand;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.NoSubjectException;

public class QuizTopicCommand extends TopicCommand {
    private String fullcommand;

    public QuizTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public String getFullcommand() {
        return this.fullcommand;
    }

    public Topic execute(Subject subject) throws NoSubjectException {
        String[] message = this.fullcommand.split(" ");
        Topic quizTopic = null;
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.toString().contains(message[1])) {
                quizTopic = topic;
            }
        }
        if (quizTopic == null) {
            throw new NoSubjectException();
        }
        return quizTopic;

    }

    public boolean isExit() {
        return false;
    }
}
