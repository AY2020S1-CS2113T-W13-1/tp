package seedu.revised.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.command.subjectcommand.AddSubjectCommand;
import seedu.revised.command.subjectcommand.DeleteSubjectCommand;
import seedu.revised.command.subjectcommand.FindSubjectCommand;
import seedu.revised.command.subjectcommand.ListSubjectCommand;
import seedu.revised.command.subjectcommand.ReturnSubjectCommand;
import seedu.revised.command.subjectcommand.QuizSubjectCommand;
import seedu.revised.exception.NoSubjectException;
import seedu.revised.exception.RepeatedSubjectException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubjectCommandTest {
    private SubjectList subjects;
    private AddSubjectCommand addCommand;
    private DeleteSubjectCommand deleteCommand;
    private FindSubjectCommand findCommand;
    private ReturnSubjectCommand returnCommand;
    private ListSubjectCommand listCommand;

    @BeforeEach
    void setUp() {
        subjects = new SubjectList(
                new ArrayList<>(List.of(
                        new Subject("English"),
                        new Subject("Chinese"),
                        new Subject("Science")
                )));
    }

    @Test
    public void addSubject() throws NoSubjectException, RepeatedSubjectException {
        addCommand = new AddSubjectCommand("add Maths");
        addCommand.execute(subjects);
        assertEquals("Maths", subjects.getList().get(3).getTitle());
    }

    @Test
    public void deleteSubject() {
        deleteCommand = new DeleteSubjectCommand("delete 2");
        deleteCommand.execute(subjects);
        assertEquals(Integer.valueOf("2"), subjects.getList().size());
    }

    @Test
    public void findSubject() {
        findCommand = new FindSubjectCommand("find English");
        findCommand.execute(subjects);
    }

    @Test
    public void listSubject() {
        listCommand = new ListSubjectCommand();
        listCommand.execute(subjects);
    }

    @Test
    public void quizSubject_command_throwsException() throws NoSubjectException {
        QuizSubjectCommand quiz = new QuizSubjectCommand("quiz Maths");
        assertThrows(NoSubjectException.class, () -> quiz.execute(subjects));

    }


    @Test
    public void returnSubject() throws NoSubjectException {
        returnCommand = new ReturnSubjectCommand("subject Chinese");
        Subject testSubject = returnCommand.execute(subjects);
        assertEquals("Chinese", testSubject.getTitle());
    }
}
