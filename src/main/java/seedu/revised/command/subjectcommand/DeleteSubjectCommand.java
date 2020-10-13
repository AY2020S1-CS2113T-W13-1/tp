package seedu.revised.command.subjectcommand;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.ui.Ui;

public class DeleteSubjectCommand extends SubjectCommand {
    private String fullCommand;

    public DeleteSubjectCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Deletes a Task in a <code>taskList</code>.
     *
     * @param subjectList the <code>TaskList</code> instance of the TaskList class for the user to delete from
     * @return
     */
    public Subject execute(SubjectList subjectList) throws NumberFormatException {
        String[] message = this.fullCommand.split(" ");
        int number = Integer.valueOf(message[1]);
        Subject subject = subjectList.getList().get(number - 1);
        assert !(number <= 0 && number > subjectList.getList().size());
        subjectList.getList().remove(number - 1);
        Ui.printSubjectDelete(subject, subjectList.getList().size());
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
