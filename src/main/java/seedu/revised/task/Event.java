package seedu.revised.task;

public class Event extends Task {
    protected String at;

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public void printEvent(TaskList taskList) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n  " + this + "\n"
                + "Now you have " + taskList.getList().size() + (taskList.getList().size() == 1
                ? " task in the list.\n" : " tasks in the list.\n")
                + "____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String getAt() {
        return at;
    }
}
