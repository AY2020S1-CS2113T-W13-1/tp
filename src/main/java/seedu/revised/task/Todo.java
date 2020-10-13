package seedu.revised.task;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public void printTodo(TaskList taskList) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n  " + this + "\n"
                + "Now you have " + taskList.getList().size() + (taskList.getList().size() == 1
                ? " task in the list.\n" : " tasks in the list.\n")
                + "____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
