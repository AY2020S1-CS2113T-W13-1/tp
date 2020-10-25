package seedu.revised.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import seedu.revised.card.Flashcard;
import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.exception.storage.DataLoadingException;
import seedu.revised.card.quiz.Result;
import seedu.revised.task.Deadline;
import seedu.revised.task.Event;
import seedu.revised.task.Task;
import seedu.revised.task.Todo;
import seedu.revised.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class Storage {
    private final File baseDir;
    private final File exportDir;
    private final String flashcardFilename;
    private final String taskFilename;
    private final String resultFilename;
    private final String exportFilename;

    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    private Storage(StorageBuilder builder) {
        this.baseDir = new File(builder.baseDir);
        this.exportDir = new File(builder.exportDir);
        this.flashcardFilename = builder.flashcardFilename;
        this.taskFilename = builder.taskFilename;
        this.resultFilename = builder.resultFilename;
        this.exportFilename = builder.exportFilename;
    }

    /**
     * Loads and populates subject data from the storage. Subjects and topics will be sorted by their titles in
     * alphabetical order.
     *
     * @throws DataLoadingException if fails to load the saved data due to filesystem error
     */
    public List<Subject> loadSubjects() throws DataLoadingException {
        logger.info("Loading subject data and its contents from the disk.");
        List<Subject> subjects;
        if (!baseDir.exists()) {  // if the data hasn't been saved before
            logger.info(String.format("%s folder is not found, no saved data is loaded.", baseDir.getAbsolutePath()));
            subjects = new ArrayList<>();
        } else {
            File[] subjectDirs = baseDir.listFiles(File::isDirectory);
            if (subjectDirs == null) {  // error in getting the directories even if they may exist
                throw new DataLoadingException(Ui.DATA_LOADING_EXCEPTION);
            }
            subjects = loadSubjects(subjectDirs);
        }

        logger.info("Finish loading subject data.");
        logger.fine(String.format("Returning subjects: %s.", subjects));
        return subjects;
    }

    /**
     * Creates a list of subjects from the saved directories and populates each subject with existing topics, results,
     * and flashcards. Subjects and topics will be sorted by their titles in alphabetical order.
     *
     * @param subjectDirs directories of subjects saved previously
     * @return a list of populated subjects loaded from the disk
     * @throws DataLoadingException if fails to load the saved data due to filesystem error
     */
    private List<Subject> loadSubjects(File[] subjectDirs) throws DataLoadingException {
        logger.fine(String.format("Loading subject data from the directories: %s.", Arrays.toString(subjectDirs)));
        List<Subject> subjects = new ArrayList<>();
        for (File subjectDir : subjectDirs) {
            File[] topicDirs = subjectDir.listFiles(File::isDirectory);
            if (topicDirs == null) {
                throw new DataLoadingException(Ui.DATA_LOADING_EXCEPTION);
            }

            List<Topic> topics = loadTopics(topicDirs);
            List<Task> tasks;
            try {
                tasks = loadTasks(subjectDir.toPath());
            } catch (FileNotFoundException e) {
                logger.log(Level.INFO, "Task file is not found under %s, proceeding with an empty task list.", e);
                tasks = new ArrayList<>();  // task file may have been deleted by the user
            }
            File resultFile = new File(subjectDir.toString(), getResultFilename());
            List<Result> results = loadResults(resultFile);
            Subject subject = new Subject(subjectDir.getName(), topics, tasks, results);
            subjects.add(subject);

        }
        subjects.sort(Comparator.comparing(Subject::getTitle));
        logger.fine(String.format("Finish loading subject data. Subjects: %s.", subjects));
        return subjects;
    }

    /**
     * Creates a list of topics from the saved directories and populates each topic with the existing flashcards
     * and results. Topics will be sorted by their titles in alphabetical order.
     *
     * @param topicDirs directories of topics saved previously
     * @return a list of populated topics loaded from the disk
     */
    private List<Topic> loadTopics(File[] topicDirs) {
        logger.fine(String.format("Loading topic data from the directories: %s.", Arrays.toString(topicDirs)));
        List<Topic> topics = new ArrayList<>();
        for (File topicDir : topicDirs) {
            File flashcardFile = new File(topicDir, getFlashcardFilename());
            File resultFile = new File(topicDir, getResultFilename());
            List<Flashcard> flashcards = loadFlashcards(flashcardFile);
            List<Result> results = loadResults(resultFile);

            Topic topic = new Topic(topicDir.getName(), flashcards, results);
            topics.add(topic);
        }
        topics.sort(Comparator.comparing(Topic::getTitle));
        logger.fine(String.format("Finish loading topic data. Topics: %s.", topics));
        return topics;
    }

    /**
     * Loads the json data in the file into an ArrayList of objects (of type specified).
     *
     * @param type     the type of the object inside the json file
     * @param jsonFile the file that stores the flashcard data
     * @return a list of populated objects with type specified loaded from the file
     */
    public static <T> List<T> loadFromJson(Type type, File jsonFile) {
        logger.info(String.format("Loading data of type %s from %s.", type, jsonFile.getAbsolutePath()));
        Gson gson = new Gson();
        List<T> objects;

        try (FileReader fileReader = new FileReader(jsonFile)) {
            objects = gson.fromJson(fileReader, type);
        } catch (IOException e) {  // file may have been deleted by the user
            logger.info(String.format("%s is not found, proceeding with returning an empty list.",
                    jsonFile.getAbsolutePath()));
            objects = new ArrayList<>();
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException("Error reading the json data at " + jsonFile.getAbsolutePath()
                    + ". Make sure the syntax is correct if you changed it manually.", e);
        }

        assert objects != null;
        logger.info("Finish loading data.");
        logger.fine(String.format("Data: %s.", objects));
        return objects;
    }

    /**
     * Loads the data in the file into an ArrayList of Flashcard.
     *
     * @param flashcardFile the file that stores the flashcard data
     * @return a list of populated flashcards loaded from the file
     */
    private List<Flashcard> loadFlashcards(File flashcardFile) {
        logger.fine(String.format("Loading flashcards from %s.", flashcardFile));
        Type objectType = new TypeToken<ArrayList<Flashcard>>() {}.getType();
        List<Flashcard> flashcards = loadFromJson(objectType, flashcardFile);
        logger.fine(String.format("Finish loading flashcards: %s.", flashcards));
        return flashcards;
    }

    /**
     * Loads the data in the file into an ArrayList of Result.
     *
     * @param resultFile the file that stores the result data
     * @return a list of populated results loaded from the file
     */
    private List<Result> loadResults(File resultFile) {
        logger.fine(String.format("Loading results from %s.", resultFile));
        Type objectType = new TypeToken<ArrayList<Result>>() {}.getType();
        List<Result> results = loadFromJson(objectType, resultFile);
        logger.fine(String.format("Finish loading results: %s.", results));
        return results;
    }

    /**
     * Saves the subjects along with all the contents into the storage. Quiz results under the subject will
     * also be saved.
     *
     * @param subjects subjects to be saved
     * @throws IOException if fails to save to the storage
     */
    public void saveSubjects(List<Subject> subjects) throws IOException {
        logger.info("Saving the subject data to the disk.");
        logger.fine(String.format("Subjects: %s.", subjects));

        assert subjects != null;
        for (Subject subject : subjects) {
            Path subjectPath = Paths.get(getBaseDir().toString(), subject.getTitle());
            Files.createDirectories(subjectPath);

            File resultFile = new File(subjectPath.toString(), getResultFilename());
            saveToJson(resultFile, subject.getResults().getList());
            saveTasks(subjectPath, subject.getTasks().getList());
            saveTopics(subjectPath, subject.getTopics().getList());
        }
        logger.info("Finish saving the subjects to the disk.");
    }

    /**
     * Saves the topics along with all the contents into the storage. If the topic has no flashcards in it, the file
     * with name {@link Storage#getFlashcardFilename()} with an empty square bracket will be created under it. Similarly
     * , the quiz result will be stored under the path with name {@link Storage#getResultFilename()}.
     *
     * @param subjectPath subject directory where topics will be stored under
     * @param topics      topics to be saved
     * @throws IOException if fails to save to the storage
     */
    public void saveTopics(Path subjectPath, List<Topic> topics) throws IOException {
        assert topics != null;
        logger.info(String.format("Saving list of topics to %s.", subjectPath.toAbsolutePath()));
        logger.fine(String.format("Topics: %s.", topics));
        for (Topic topic : topics) {
            Path topicPath = Paths.get(subjectPath.toString(), topic.getTitle());
            Files.createDirectories(topicPath);

            File flashcardFile = new File(topicPath.toString(), getFlashcardFilename());
            File resultFile = new File(topicPath.toString(), getResultFilename());
            saveToJson(flashcardFile, topic.getFlashcards());
            saveToJson(resultFile, topic.getResults().getList());
        }
        logger.info("Finish saving the list of topics.");
    }

    /**
     * Save the contents of the list of objects to the file path provided.
     * This overwrites the content of the file if it already exists.
     *
     * @param jsonFile File where the objects will be stored into as json
     * @param objects  list of objects
     * @throws IOException if fails to save to the storage
     */
    public static <T> void saveToJson(File jsonFile, List<T> objects) throws IOException {
        logger.info(String.format("Saving list of data to %s.", jsonFile.getAbsolutePath()));
        logger.fine(String.format("Objects: %s.", objects));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            gson.toJson(objects, fileWriter);  // store the json to file
            fileWriter.flush();  // flush to actually write the content
        }
        logger.info("Finish saving the list of data.");
    }

    /**
     * Saves the tasks into the file with name {@link Storage#getTaskFilename()} under the subjectPath.
     * This overwrites the content of the file if it already exists.
     *
     * @param subjectPath subject directory where the tasks will be stored under
     * @throws IOException if there is an error writing to the file
     */
    public void saveTasks(Path subjectPath, List<Task> tasks) throws IOException {
        File taskFile = new File(subjectPath.toString(), getTaskFilename());
        logger.info(String.format("Saving tasks to %s", taskFile.getAbsolutePath()));
        try (FileWriter fileWriter = new FileWriter(taskFile)) {
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    fileWriter.write("T | " + (task.getIsDone() ? "1" : "0") + " | "
                            + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    fileWriter.write("D | " + (task.getIsDone() ? "1" : "0") + " | "
                            + task.getDescription() + " | " + ((Deadline) task).getDateTimeDescription() + "\n");
                } else if (task instanceof Event) {
                    fileWriter.write("E | " + (task.getIsDone() ? "1" : "0") + " | "
                            + task.getDescription() + " | " + ((Event) task).getDateTimeDescription() + "\n");
                }
            }
        }
        logger.info("Finish saving tasks.");
    }

    /**
     * Reads the task file contents under the specified subject into a list of tasks.
     *
     * @param subjectPath subject directory where the tasks were stored under
     * @return a list of previously saved tasks
     * @throws FileNotFoundException when there are no files found
     */
    public List<Task> loadTasks(Path subjectPath) throws FileNotFoundException {
        File taskFile = new File(subjectPath.toString(), getTaskFilename());
        logger.info(String.format("Loading tasks from %s.", taskFile.getAbsolutePath()));
        List<Task> tasks = new ArrayList<>();

        try (Scanner scan = new Scanner(taskFile)) {
            while (scan.hasNextLine()) {
                String content = scan.nextLine();
                String[] contents = content.split("\\s\\|\\s");
                String legend = contents[0].trim();
                boolean done = Integer.parseInt(contents[1].trim()) == 1;
                String action = contents[2].trim();
                String action2;
                LocalDateTime dateTime = null;
                DateTimeFormatter format = DateTimeFormatter.ofPattern("h:mm a d MMM yyyy");

                if (legend.equals("D") || legend.equals("E")) {
                    action2 = contents[3].trim();
                    dateTime = LocalDateTime.parse(action2, format);

                }

                switch (legend) {
                case "T":
                    tasks.add(new Todo(action, done));
                    break;
                case "D":
                    tasks.add(new Deadline(action, done, dateTime));
                    break;
                case "E":
                    tasks.add(new Event(action, done, dateTime));
                    break;
                default:
                    assert false : legend;
                }
            }
        }
        logger.info("Finish loading tasks.");
        logger.fine(String.format("Tasks: %s.", tasks));
        return tasks;
    }

    /**
     * Export the subjects with all their contents into one json file. The file location is specified by
     * {@link Storage#getExportDir()}/{@link Storage#getExportFilename()}.
     *
     * @param subjects list of subjects to be saved to the storage
     * @return the file that the data has been exported to
     * @throws IOException if fails to save to the file
     */
    public File export(List<Subject> subjects) throws IOException {
        File file = new File(getExportDir().toString(), getExportFilename());
        logger.info(String.format("Exporting the subject data to %s.", file.getAbsolutePath()));

        Files.createDirectories(getExportDir().toPath());  // create export directory
        saveToJson(file, subjects);
        logger.info("Finish exporting data.");
        return file;
    }

    public File getBaseDir() {
        return baseDir;
    }

    public File getExportDir() {
        return exportDir;
    }

    public String getFlashcardFilename() {
        return flashcardFilename;
    }

    public String getTaskFilename() {
        return taskFilename;
    }

    public String getResultFilename() {
        return resultFilename;
    }

    public String getExportFilename() {
        return exportFilename;
    }

    public static class StorageBuilder {
        private String baseDir;
        private String exportDir;
        private String flashcardFilename;
        private String taskFilename;
        private String resultFilename;
        private String exportFilename;

        /**
         * Set baseDir property of the Storage to be built.
         *
         * @param baseDir the name of the directory to store the data into
         */
        public StorageBuilder setBaseDir(String baseDir) {
            this.baseDir = baseDir;
            return this;
        }

        /**
         * Set exportDir property of the Storage to be built.
         *
         * @param exportDir the name of the directory to export the data to
         */
        public StorageBuilder setExportDir(String exportDir) {
            this.exportDir = exportDir;
            return this;
        }

        /**
         * Set flashcardFilename property of the Storage to be built.
         *
         * @param flashcardFilename the name of the file to store all the flashcard info
         */
        public StorageBuilder setFlashcardFilename(String flashcardFilename) {
            this.flashcardFilename = flashcardFilename;
            return this;
        }

        /**
         * Set taskFilename property of the Storage to be built.
         *
         * @param taskFilename the name of the file to store all the tasks under a subject
         */
        public StorageBuilder setTaskFilename(String taskFilename) {
            this.taskFilename = taskFilename;
            return this;
        }

        /**
         * Set resultFilename property of the Storage to be built.
         *
         * @param resultFilename the name of the file to store all the results of quizzes
         */
        public StorageBuilder setResultFilename(String resultFilename) {
            this.resultFilename = resultFilename;
            return this;
        }

        /**
         * Set exportFilename property of the Storage to be built.
         *
         * @param exportFilename the name of the file that the data will be exported to
         */
        public StorageBuilder setExportFilename(String exportFilename) {
            this.exportFilename = exportFilename;
            return this;
        }

        /**
         * Build a Storage object with all the properties previously set. All the properties must be set before
         * calling this function or an exception will be thrown.
         *
         * @return a Storage object with all the properties set.
         */
        public Storage build() {
            Storage storage = new Storage(this);
            if (storage.getBaseDir() == null || storage.getExportDir() == null
                    || storage.getFlashcardFilename() == null || storage.getTaskFilename() == null
                    || storage.getResultFilename() == null || storage.getExportFilename() == null) {
                throw new IllegalArgumentException();
            }

            return storage;
        }
    }
}
