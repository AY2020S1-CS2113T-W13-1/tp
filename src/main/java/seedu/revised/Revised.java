package seedu.revised;

import seedu.revised.card.Subject;
import seedu.revised.card.SubjectList;
import seedu.revised.card.quiz.ResultList;
import seedu.revised.card.quiz.SubjectQuiz;
import seedu.revised.command.subject.ExportCommand;
import seedu.revised.command.subject.QuizSubjectCommand;
import seedu.revised.command.subject.ReturnSubjectCommand;
import seedu.revised.command.subject.SubjectCommand;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.storage.DataLoadingException;
import seedu.revised.exception.subject.InvalidSubjectException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.parser.SubjectParser;
import seedu.revised.storage.Storage;
import seedu.revised.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Revised {
    public static final String BASE_DIR = "data";
    public static final String EXPORT_DIR = "export";
    public static final String FLASHCARD_FILENAME = "flashcards.json";
    public static final String TASK_FILENAME = "tasks.txt";
    public static final String RESULT_FILENAME = "results.json";
    public static final String EXPORT_FILENAME = "data.json";

    private Storage storage;
    private SubjectList subjects;
    private ResultList results;

    /**
     * Initialises Duke by loading saved data from the disk, if any.
     *
     * @param baseDir           the name of the directory to store the data into
     * @param exportDir         the name of the directory to export the data to
     * @param flashcardFilename the name of the file to store all the flashcard info
     * @param taskFilename      the name of the file to store all the tasks under a subject
     * @param resultFilename    the name of the file to store all the results of quizzes
     * @param exportFilename    the name of the file that the data will be exported to
     */
    public Revised(String baseDir, String exportDir, String flashcardFilename, String taskFilename,
                   String resultFilename, String exportFilename) throws DataLoadingException {
        storage = new Storage.StorageBuilder()
                .setBaseDir(baseDir)
                .setExportDir(exportDir)
                .setFlashcardFilename(flashcardFilename)
                .setTaskFilename(taskFilename)
                .setResultFilename(resultFilename)
                .setExportFilename(exportFilename)
                .build();
        subjects = new SubjectList(storage.loadSubjects());
        results = new ResultList(new ArrayList<>());
    }

    /**
     * Runs the Duke program until the user exits the program.
     */
    public void run() {
        Ui.printStart(subjects);
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
                } else if (c instanceof ExportCommand) {  // TODO: bad practice, shouldn't use instanceof here
                    ((ExportCommand) c).execute(subjects, storage);
                } else {
                    c.execute(subjects);
                }
                isExit = c.isExit();
            }  catch (NoSubjectException e) {
                System.out.println(e.getMessage());
            } catch (RepeatedSubjectException e) {
                System.out.println(e.getMessage());
            } catch (NoFlashcardException e) {
                System.out.println(e.getMessage());
            } catch (NoTopicException e) {
                System.out.println(e.getMessage());
            } catch (InvalidSubjectException e) {
                System.out.println(e.getMessage());
            } catch (FailedParseException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(Ui.printOutOfBoundsError());
            } catch (NumberFormatException e) {
                System.out.println(Ui.printIndexError());
            } catch (Exception e) {
                System.out.println(Ui.printError(e));
            }
        }

        try {
            storage.saveSubjects(subjects.getList());
        } catch (IOException e) {
            System.out.println(Ui.printWritingError());
        }

        Ui.printBye();
    }

    public static void main(String[] args) {
        try {
            new Revised(BASE_DIR, EXPORT_DIR, FLASHCARD_FILENAME, TASK_FILENAME, RESULT_FILENAME, EXPORT_FILENAME)
                    .run();
        } catch (DataLoadingException e) {
            System.out.println(e.getMessage());
        }
    }
}
