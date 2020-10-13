package seedu.revised.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.revised.card.Flashcard;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.DataLoadingException;
import seedu.revised.exception.FlashcardSyntaxException;
import seedu.revised.task.Deadline;
import seedu.revised.task.Event;
import seedu.revised.task.Task;
import seedu.revised.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StorageTest {
    private List<Subject> subjects;
    private List<Topic> topics;
    private List<Flashcard> flashcards;
    private List<Task> tasks;
    private List<String> tasksStr;
    private Storage storage;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        // use tempDir as baseDir to avoid file creation for subjects
        storage = new Storage(tempDir.toString(), "flashcard.txt", "tasks.txt");

        subjects = new ArrayList<>(List.of(
                new Subject("subject1"),
                new Subject("subject2"),
                new Subject("subject3")
        ));
        topics = new ArrayList<>(List.of(
                new Topic("topic1"),
                new Topic("topic2"),
                new Topic("topic3")
        ));
        flashcards = new ArrayList<>(List.of(
                new Flashcard("question1", "answer1"),
                new Flashcard("question2", "answer2"),
                new Flashcard("question3", "answer3")
        ));
        tasks = new ArrayList<>(List.of(
                new Todo("todoTask", false),
                new Event("eventTask", true, "atTime"),
                new Deadline("deadlineTask", false, "byTime")
        ));
        tasksStr = new ArrayList<>(List.of(
                "T | 0 | todoTask",
                "E | 1 | eventTask | atTime",
                "D | 0 | deadlineTask | byTime"
        ));
    }

    @Test
    void saveSubjects_subjectsWithoutTopics_DirectoriesWithSubjectTitlesAndTasksFileCreated() throws IOException {
        storage.saveSubjects(subjects);
        for (Subject subject : subjects) {
            File subjectDir = Paths.get(storage.getBaseDir().toString(), subject.getTitle()).toFile();
            assertTrue(subjectDir.exists());
            assertTrue(subjectDir.isDirectory());

            File taskFile = new File(subjectDir, storage.getTaskFilename());
            assertTrue(taskFile.exists());
        }
    }

    @Test
    void saveSubjects_subjectsWithTopics_DirectoriesWithEveryHierarchyCreated() throws IOException {
        // populate subjects
        for (Subject subject : subjects) {
            for (Topic topic : topics) {
                subject.getTopics().add(topic);
            }
        }

        storage.saveSubjects(subjects);
        for (Subject subject : subjects) {
            File subjectDir = Paths.get(storage.getBaseDir().toString(), subject.getTitle()).toFile();
            assertTrue(subjectDir.exists());
            assertTrue(subjectDir.isDirectory());

            File taskFile = new File(subjectDir, storage.getTaskFilename());
            assertTrue(taskFile.exists());
            // check if subdirectories are created
            File[] topicDirs = subjectDir.listFiles(File::isDirectory);
            assertNotNull(topicDirs);
            assertEquals(subject.getTopics().getList().size(), topicDirs.length);
        }
    }

    @Test
    void saveTopics_topicsWithoutFlashcards_DirectoriesWithTopicTitlesAndEmptyFileCreated() throws IOException {
        storage.saveTopics(tempDir, topics);
        for (Topic topic : topics) {
            File topicDir = Paths.get(tempDir.toString(), topic.getTitle()).toFile();
            File flashcardFile = new File(topicDir, storage.getFlashcardFilename());
            assertTrue(topicDir.exists());
            assertTrue(topicDir.isDirectory());
            assertTrue(flashcardFile.exists());
            assertEquals("[]", Files.readString(flashcardFile.toPath()));
        }
    }

    @Test
    void saveTopics_topicsWithFlashcards_DirectoriesWithTopicTitlesAndPopulatedFileCreated() throws IOException {
        // populate topics with flashcards
        for (Topic topic : topics) {
            for (Flashcard flashcard : flashcards) {
                topic.addFlashcard(flashcard);
            }
        }

        storage.saveTopics(tempDir, topics);
        for (Topic topic : topics) {
            File topicDir = Paths.get(tempDir.toString(), topic.getTitle()).toFile();
            File flashcardFile = new File(topicDir, storage.getFlashcardFilename());
            assertTrue(topicDir.exists());
            assertTrue(topicDir.isDirectory());
            assertTrue(flashcardFile.exists());
            assertNotEquals("[]", Files.readString(flashcardFile.toPath()));
        }
    }

    @Test
    void saveFlashcards_validFlashcards_readableJsonIsStored() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path file = Paths.get(tempDir.toString(), storage.getFlashcardFilename());

        storage.saveFlashcards(tempDir, flashcards);

        assertTrue(file.toFile().exists());
        assertEquals(gson.toJson(flashcards), Files.readString(file));
    }

    @Test
    void loadSubjects_noSavedData_emptyListOfSubjectsReturned() throws FlashcardSyntaxException, DataLoadingException {
        assertTrue(storage.loadSubjects().isEmpty());
    }

    @Test
    void loadSubjects_validStructure_populatedSubjects() throws IOException, FlashcardSyntaxException,
            DataLoadingException {
        // create valid directory structure
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (Subject subject : subjects) {
            for (Topic topic : topics) {
                Path topicPath = Paths.get(storage.getBaseDir().toString(), subject.getTitle(), topic.getTitle());
                Files.createDirectories(topicPath);
                File flashcardFile = Paths.get(topicPath.toString(), storage.getFlashcardFilename()).toFile();

                // write flashcards to file
                try (FileWriter fileWriter = new FileWriter(flashcardFile)) {
                    gson.toJson(flashcards, fileWriter);  // store the json to file
                    fileWriter.flush();  // flush to actually write the content
                }
            }
        }

        List<Subject> loadedSubjects = storage.loadSubjects();
        assertEquals(loadedSubjects.size(), subjects.size());
        for (Subject subject : loadedSubjects) {
            assertEquals(subject.getTopics().getList().size(), topics.size());
            for (Topic topic : subject.getTopics().getList()) {
                assertEquals(topic.getFlashcards().size(), flashcards.size());
            }
        }
    }

    @Test
    void saveTasks_tasks_correctFormatInFile() throws IOException {
        Path subjectPath = storage.getBaseDir().toPath();
        storage.saveTasks(subjectPath, tasks);
        File writtenFile = new File(subjectPath.toString(), storage.getTaskFilename());

        try (Scanner scanner = new Scanner(writtenFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                assertTrue(tasksStr.stream().anyMatch(s -> s.equals(line)));
            }
        }
    }

    @Test
    void loadTasks_taskFile_correctlyParsedTasks() throws IOException {
        Path subjectPath = storage.getBaseDir().toPath();
        File taskFile = new File(subjectPath.toString(), storage.getTaskFilename());
        try (FileWriter fileWriter = new FileWriter(taskFile)) {
            for (String taskStr : tasksStr) {
                fileWriter.write(taskStr + "\n");
            }
        }

        List<Task> savedTasks = storage.loadTasks(subjectPath);
        for (int i = 0; i < savedTasks.size(); i++) {
            Task t1 = savedTasks.get(i);
            Task t2 = tasks.get(i);
            assertEquals(t1.getDescription(), t2.getDescription());
            assertEquals(t1.getIsDone(), t2.getIsDone());
            if (t1 instanceof Event) {
                assertEquals(((Event) t1).getAt(), ((Event) t2).getAt());
            } else if (t1 instanceof Deadline) {
                assertEquals(((Deadline) t1).getBy(), ((Deadline) t2).getBy());
            }
        }
    }


}