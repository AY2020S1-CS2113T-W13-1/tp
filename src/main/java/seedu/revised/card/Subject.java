package seedu.revised.card;

import seedu.revised.card.quiz.ResultList;
import seedu.revised.task.Task;
import seedu.revised.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String title;
    private TopicList topics;
    private TaskList tasks;
    private ResultList results;

    public Subject(String title) {
        this.title = title;
        this.topics = new TopicList(new ArrayList<>());
        results = new ResultList(new ArrayList<>());
        tasks = new TaskList(new ArrayList<>());
    }

    public Subject(String title, List<Topic> topics) {
        this.title = title;
        this.topics = new TopicList(topics);
        tasks = new TaskList(new ArrayList<>());
        results = new ResultList(new ArrayList<>());
    }

    public Subject(String title, List<Topic> topics, List<Task> tasks) {
        this.title = title;
        this.topics = new TopicList(topics);
        this.tasks = new TaskList(tasks);
        this.results = new ResultList(new ArrayList<>());
    }

    public Subject(String title, TopicList topics, TaskList tasks, ResultList results) {
        this.title = title;
        this.topics = topics;
        this.tasks = tasks;
        this.results = results;
    }

    public void printSubject(SubjectList subjectList) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this subject:\n  " + this + "\n"
                + "Now you have " + subjectList.getList().size() + (subjectList.getList().size() == 1
                ? " subject in the list.\n" : " subjects in the list.\n")
                + "____________________________________________________________");
    }

    public String getTitle() {
        return title;
    }

    public TopicList getTopics() {
        return topics;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public ResultList getResults() {
        return results;
    }

    @Override
    public String toString() {
        return this.title;
    }


}
