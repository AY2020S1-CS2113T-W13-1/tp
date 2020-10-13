package seedu.revised.card;

import java.util.List;

public class Flashcard {

    private String question;
    private String answer;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void printFlashcard(List flashcards) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this flashcard:\n  " + this.question + "; " + this.answer +  "\n"
                + "Now you have " + flashcards.size() + (flashcards.size() == 1
                ? " flashcard in the list.\n" : " flashcards in the list.\n")
                + "____________________________________________________________");
    }
}
