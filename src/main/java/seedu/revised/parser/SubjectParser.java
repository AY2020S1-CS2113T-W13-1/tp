package seedu.revised.parser;

import seedu.revised.command.subjectcommand.AddSubjectCommand;
import seedu.revised.command.subjectcommand.DeleteSubjectCommand;
import seedu.revised.command.subjectcommand.ExitSubjectCommand;
import seedu.revised.command.subjectcommand.FindSubjectCommand;
import seedu.revised.command.subjectcommand.ListSubjectCommand;
import seedu.revised.command.subjectcommand.ReturnSubjectCommand;
import seedu.revised.command.subjectcommand.SorrySubjectCommand;
import seedu.revised.command.subjectcommand.SubjectCommand;
import seedu.revised.command.subjectcommand.QuizSubjectCommand;
import seedu.revised.command.subjectcommand.ResultSubjectCommand;

/**
 * Allows the parsing of inputs provided by the user.
 */
public class SubjectParser {

    /**
     * Parses the inputs provided by the user.
     *
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static SubjectCommand parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitSubjectCommand();
        } else if (fullCommand.equals("list")) {
            return new ListSubjectCommand();
        } else if (fullCommand.startsWith("add")) {
            return new AddSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("find")) {
            return new FindSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("subject")) {
            return new ReturnSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("quiz")) {
            return new QuizSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("results")) {
            return new ResultSubjectCommand(fullCommand);
        } else {
            return new SorrySubjectCommand();
        }
    }
}