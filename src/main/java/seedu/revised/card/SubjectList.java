package seedu.revised.card;

import java.util.List;

public class SubjectList {
    private List<Subject> subjectList;

    public SubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    /**
     * Adds a <code>Task</code> into a <code>TaskList</code>.
     *
     * @param subject The <code>Subject</code> to add into a <code>SubjectList</code>
     */
    public void add(Subject subject) {
        this.subjectList.add(subject);
    }

    /**
     * Gets a <code>ArrayList</code> of Subjects in a <code>SubjectList</code> instance.
     *
     * @return the <code>ArrayList</code> of Subjects
     */
    public List<Subject> getList() {
        return this.subjectList;
    }

}
