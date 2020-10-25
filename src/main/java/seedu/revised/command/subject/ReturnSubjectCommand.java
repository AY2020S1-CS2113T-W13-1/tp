package seedu.revised.command.subject;

import seedu.revised.card.Subject;
import seedu.revised.card.Topic;
import seedu.revised.card.quiz.TopicQuiz;
import seedu.revised.command.Command;
import seedu.revised.command.task.TaskCommand;
import seedu.revised.command.topic.QuizTopicCommand;
import seedu.revised.command.topic.ReturnTopicCommand;
import seedu.revised.command.topic.TopicCommand;
import seedu.revised.card.SubjectList;
import seedu.revised.exception.FailedParseException;
import seedu.revised.exception.flashcard.NoFlashcardException;
import seedu.revised.exception.subject.NoSubjectException;
import seedu.revised.exception.subject.RepeatedSubjectException;
import seedu.revised.exception.task.RepeatedDateTimeException;
import seedu.revised.exception.task.TaskDeadlineException;
import seedu.revised.exception.task.TaskEventException;
import seedu.revised.exception.task.TaskTodoException;
import seedu.revised.exception.topic.InvalidTopicException;
import seedu.revised.exception.topic.NoTopicException;
import seedu.revised.parser.TopicParser;
import seedu.revised.task.TaskList;
import seedu.revised.ui.Ui;

import java.time.format.DateTimeParseException;

public class ReturnSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public ReturnSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException {
        String[] message = this.fullcommand.split(" ");
        //Subject currentSubject = new Subject(message[1]);
        if (message[1].isEmpty()) {
            throw new NoSubjectException(Ui.NO_SUBJECT_EXCEPTION);
        }
        for (Subject subject : subjectList.getList()) {
            if (subject.getTitle().equals(message[1])) {
                return subject;
            }
        }
        throw new NoSubjectException(Ui.NO_SUBJECT_EXCEPTION);
    }

    public void goToSubject(Subject subject) {
        Ui.printGoToSubject(subject);
        boolean isSubjectExit = false;
        while (!isSubjectExit) {
            try {
                String fullCommand = Ui.readCommand();
                Command c = TopicParser.parse(fullCommand);
                if (c instanceof ReturnTopicCommand) {
                    Topic topic = ((ReturnTopicCommand) c).execute(subject);
                    ((ReturnTopicCommand) c).goToTopic(topic, subject);
                } else if (c instanceof QuizTopicCommand) {
                    Topic topic = ((QuizTopicCommand) c).execute(subject);
                    TopicQuiz topicQuiz = new TopicQuiz(topic);
                    topicQuiz.startQuiz(topic.getResults());
                } else if (c instanceof TopicCommand) {
                    ((TopicCommand) c).execute(subject);
                } else if (c instanceof TaskCommand) {
                    TaskList taskList = subject.getTasks();
                    ((TaskCommand) c).execute(taskList);
                }

                isSubjectExit = c.isExit();

            } catch (NumberFormatException e) {
                Ui.printErrorMsg(Ui.INDEX_FORMAT_EXCEPTION);
            } catch (IndexOutOfBoundsException e) {
                Ui.printErrorMsg(Ui.INDEX_OUT_OF_BOUND_EXCEPTION);
            } catch (IllegalArgumentException | DateTimeParseException d) {
                Ui.printErrorMsg(Ui.INVALID_DATETIME_EXCEPTION);
            } catch (Exception e) {
                Ui.printError(e);
            }
        }
        Ui.printBackToSubjects();

    }

    public boolean isExit() {
        return false;
    }
}
