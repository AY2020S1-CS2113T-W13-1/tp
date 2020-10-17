package seedu.revised.command.topic;

public class ExitTopicCommand extends TopicCommand {
    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return true;
    }
}
