package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.command.subject.QuizSubjectCommand;
import seedu.revised.exception.topic.InvalidTopicException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class QuizTopicCommand extends TopicCommand {
    private static final Logger logger = Logger.getLogger(QuizTopicCommand.class.getName());
    private String fullcommand;

    public QuizTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public String getFullcommand() {
        return this.fullcommand;
    }

    public Topic execute(Subject subject) throws NoTopicException, InvalidTopicException {
        logger.info("Begin finding the topic for which the quiz has to be conducted.");
        String[] message = this.fullcommand.split(" ");
        if (message.length == 1) {
            throw new InvalidTopicException(Ui.INVALID_TOPIC_EXCEPTION);
        }
        Topic quizTopic = null;
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.toString().contains(message[1])) {
                quizTopic = topic;
            }
        }
        if (quizTopic == null) {
            throw new NoTopicException(Ui.TOPIC_NOT_FOUND_EXCEPTION);
        }
        logger.info("Finish reading the command to find the topic for the quiz feature");
        logger.fine(String.format("The subject is %s",quizTopic.getTitle()));
        return quizTopic;

    }

    public boolean isExit() {
        return false;
    }
}
