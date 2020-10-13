package seedu.revised;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.card.quiz.ResultList;
import seedu.revised.card.quiz.SubjectQuiz;
import seedu.revised.command.subjectcommand.QuizSubjectCommand;
import seedu.revised.command.subjectcommand.ReturnSubjectCommand;
import seedu.revised.command.subjectcommand.SubjectCommand;
import seedu.revised.exception.DataLoadingException;
import seedu.revised.exception.FlashcardSyntaxException;
import seedu.revised.exception.NoFlashCardException;
import seedu.revised.exception.NoSubjectException;
import seedu.revised.exception.NoTopicException;
import seedu.revised.exception.RepeatedSubjectException;
import seedu.revised.parser.SubjectParser;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Revised {
    public static final String BASE_DIR = "data";
    public static final String FLASHCARD_FILENAME = "flashcards.txt";
    public static final String TASK_FILENAME = "tasks.txt";

    private Storage storage;
    private SubjectList subjects;
    private ResultList results;

    /**
     * Initialises Duke by loading saved data from the disk, if any.
     *
     * @param baseDir           the name of the directory to store the data into
     * @param flashcardFilename the name of the file to store all the flashcard info
     * @param taskFilename      the name of the file to store all the tasks under a subject
     */
    public Revised(String baseDir, String flashcardFilename, String taskFilename)
            throws FlashcardSyntaxException, DataLoadingException {
        storage = new Storage(baseDir, flashcardFilename, taskFilename);
        subjects = new SubjectList(storage.loadSubjects());
        results = new ResultList(new ArrayList<>());
    }

    /**
     * Runs the Duke program until the user exits the program.
     */
    public void run() {
        Ui.printStart();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                SubjectCommand c = SubjectParser.parse(fullCommand);
                if (c instanceof ReturnSubjectCommand) {
                    Subject subject = c.execute(subjects);
                    ((ReturnSubjectCommand) c).goToSubject(subject);
                } else if (c instanceof QuizSubjectCommand) {
                    Subject subject = c.execute(subjects);
                    SubjectQuiz subjectQuiz = new SubjectQuiz(subject);
                    subjectQuiz.startQuiz(results);
                } else {
                    c.execute(subjects);
                }
                isExit = c.isExit();
            } catch (NumberFormatException e) {
                Ui.printIndexError();
            } catch (NoSubjectException e) {
                Ui.printNoSubjectError();
            } catch (RepeatedSubjectException e) {
                Ui.printRepeatedSubjectError();
            } catch (IndexOutOfBoundsException e) {
                Ui.printOutOfBoundsError();
            } catch (NoFlashCardException e) {
                Ui.printNoFlashcards();
            } catch (NoTopicException e) {
                Ui.printNoTopics();
            }
        }

        try {
            storage.saveSubjects(subjects.getList());
        } catch (IOException e) {
            Ui.printWritingError();
        }

        Ui.printBye();
    }

    public static void main(String[] args) {
        try {
            new Revised(BASE_DIR, FLASHCARD_FILENAME, TASK_FILENAME).run();
        } catch (FlashcardSyntaxException | DataLoadingException e) {
            Ui.printError(e);
        }
    }
}
