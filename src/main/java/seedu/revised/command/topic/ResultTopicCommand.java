package seedu.revised.command.topic;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.topic.InvalidTopicException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.ui.Ui;

import java.util.logging.Logger;

public class ResultTopicCommand extends TopicCommand {
    private static final Logger logger = Logger.getLogger(QuizTopicCommand.class.getName());
    private String fullcommand;

    public ResultTopicCommand(String fullcommand) {
        this.fullcommand = fullcommand;

    }

    public Topic execute(Subject subject) throws NoTopicException, InvalidTopicException {
        logger.info("Begin finding the topic for which the results feature has to be called.");

        String[] message = this.fullcommand.split(" ");
        if (message.length == 1) {
            throw new InvalidTopicException(Ui.INVALID_TOPIC_EXCEPTION);
        }
        Topic resultTopic = null;
        for (Topic topic : subject.getTopics().getList()) {
            if (topic.toString().contains(message[1])) {
                resultTopic = topic;
            }
        }
        if (resultTopic == null) {
            throw new NoTopicException(Ui.TOPIC_NOT_FOUND_EXCEPTION);
        }
        logger.info("Finish reading the command to find the topic for the result feature.Now, the "
                + "application prints" + "the results.");
        logger.fine(String.format("The subject is %s", resultTopic.getTitle()));
        Ui.printTopicResults(resultTopic);

        return null;
    }
}
