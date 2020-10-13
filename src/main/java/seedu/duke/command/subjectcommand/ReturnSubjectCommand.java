package seedu.duke.command.subjectcommand;

import seedu.duke.card.Subject;
import seedu.duke.card.Topic;
import seedu.duke.card.quiz.TopicQuiz;
import seedu.duke.command.Command;
import seedu.duke.command.taskcommand.TaskCommand;
import seedu.duke.command.topiccommand.QuizTopicCommand;
import seedu.duke.command.topiccommand.ReturnTopicCommand;
import seedu.duke.command.topiccommand.TopicCommand;
import seedu.duke.card.SubjectList;
import seedu.duke.exception.NoSubjectException;
import seedu.duke.exception.RepeatedSubjectException;
import seedu.duke.exception.TaskDeadlineException;
import seedu.duke.exception.TaskEventException;
import seedu.duke.exception.TaskException;
import seedu.duke.exception.TaskTodoException;
import seedu.duke.parser.SubjectParser;
import seedu.duke.parser.TaskParser;
import seedu.duke.exception.NoFlashCardException;
import seedu.duke.parser.TopicParser;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class ReturnSubjectCommand extends SubjectCommand {
    private String fullcommand;

    public ReturnSubjectCommand(String fullcommand) {
        this.fullcommand = fullcommand;
    }

    public Subject execute(SubjectList subjectList) throws NoSubjectException {
        String[] message = this.fullcommand.split(" ");
        //Subject currentSubject = new Subject(message[1]);
        if (message[1].isEmpty()) {
            throw new NoSubjectException();
        }
        for (Subject subject : subjectList.getList()) {
            if (subject.getTitle().equals(message[1])) {
                return subject;
            }
        }
        throw new NoSubjectException();
    }

    public void goToSubject(Subject subject, TaskList tasks) {
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
                //TODO: implement the storage methods for Topic
                //topics.saveSubject(topicStorage.getFileName());
                //} catch (IOException e) {
                //Ui.printWritingError();

            } catch (NumberFormatException e) {
                Ui.printIndexError();
            } catch (NoSubjectException e) {
                Ui.printNoTopicError();
            } catch (RepeatedSubjectException e) {
                Ui.printRepeatedTopicError();
            } catch (IndexOutOfBoundsException e) {
                Ui.printOutOfBoundsError();
            } catch (TaskTodoException e) {
                Ui.printTodoError();
            } catch (TaskDeadlineException e) {
                Ui.printDeadlineError();
            } catch (TaskEventException e) {
                Ui.printEventError();
            } catch (TaskException e) {
                Ui.printError();
            } catch (NoFlashCardException e) {
                Ui.printNoFlashcards();
            }
        }
            Ui.printBackToSubjects();

    }

    public boolean isExit() {
        return false;
    }
}
