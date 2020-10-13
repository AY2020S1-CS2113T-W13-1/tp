package seedu.revised.parser;


import seedu.revised.command.Command;
import seedu.revised.command.taskcommand.AddDeadlineCommand;
import seedu.revised.command.taskcommand.AddEventCommand;
import seedu.revised.command.taskcommand.AddTodoCommand;
import seedu.revised.command.taskcommand.DeleteTaskCommand;
import seedu.revised.command.taskcommand.DoneTaskCommand;
import seedu.revised.command.topiccommand.QuizTopicCommand;
import seedu.revised.command.topiccommand.AddTopicCommand;
import seedu.revised.command.topiccommand.ExitTopicCommand;
import seedu.revised.command.topiccommand.ListTopicCommand;
import seedu.revised.command.topiccommand.SorryTopicCommand;
import seedu.revised.command.topiccommand.DeleteTopicCommand;
import seedu.revised.command.topiccommand.FindTopicCommand;
import seedu.revised.command.topiccommand.ReturnTopicCommand;
import seedu.revised.command.topiccommand.ResultTopicCommand;

/**
 * Parses the commands on the topic level.
 */
public class TopicParser {

    /**
     * Parses the inputs provided by the user.
     *
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static Command parse(String fullCommand) {
        String[] message = fullCommand.split(" ");
        switch (message[0]) {
        case "exit":
            if (fullCommand.equals("exit")) {
                return new ExitTopicCommand();
            } else {
                return new SorryTopicCommand();
            }
        case "list":
            if (fullCommand.equals("list")) {
                return new ListTopicCommand();
            } else {
                return new SorryTopicCommand();
            }
        case "add":
            return new AddTopicCommand(fullCommand);
        case "delete":
            String [] commands = fullCommand.split(" ", 2);
            if (message[1].equals("topic")) {
                return new DeleteTopicCommand(commands[1]);
            } else if (message[1].equals("task")) {
                return new DeleteTaskCommand(commands[1]);
            } else {
                return new SorryTopicCommand();
            }
        case "find":
            return new FindTopicCommand(fullCommand);
        case "topic":
            return new ReturnTopicCommand(fullCommand);
        case "todo":
            return new AddTodoCommand(fullCommand);
        case "deadline":
            return new AddDeadlineCommand(fullCommand);
        case "event":
            return new AddEventCommand(fullCommand);
        case "done":
            return new DoneTaskCommand(fullCommand);
        case "quiz":
            return new QuizTopicCommand(fullCommand);
        case "results":
            return new ResultTopicCommand(fullCommand);
        default:
            return new SorryTopicCommand();
        }
    }
}