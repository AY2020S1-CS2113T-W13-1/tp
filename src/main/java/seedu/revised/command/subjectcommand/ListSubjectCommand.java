package seedu.revised.command.subjectcommand;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.ui.Ui;

public class ListSubjectCommand extends SubjectCommand {

    public Subject execute(SubjectList subjectList) {
        Ui.printSubjectList(subjectList.getList());
        return null;
    }

    public boolean isExit() {
        return false;
    }
}
