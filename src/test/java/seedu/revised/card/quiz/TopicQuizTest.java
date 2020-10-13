package seedu.revised.card.quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.revised.card.Flashcard;
import seedu.revised.card.Topic;
import seedu.revised.card.TopicList;
import seedu.revised.exception.NoFlashCardException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TopicQuizTest {
    private TopicList topics;
    private Result result = new Result(0, 0);
    private List<Flashcard> flashcards = new ArrayList<>();
    private List<String> incorrectAnswers = new ArrayList<>();
    private ResultList results = new ResultList(new ArrayList<>());

    @BeforeEach
    void setup() {
        topics = new TopicList(
                new ArrayList<>(List.of(
                        new Topic("First War"),
                        new Topic("Evolution")
                )));

        Flashcard first = new Flashcard("middle of solar system", "Sun");
        Flashcard second = new Flashcard("lightspeed", "speed of light");
        topics.get(1).addFlashcard(first);
        topics.get(1).addFlashcard(second);

    }

    @Test
    void setUpQuiz_TopicWithoutFlashcard_throwsNoFlashcardException() {
        TopicQuiz topicQuiz = new TopicQuiz(topics.get(0));
        assertThrows(NoFlashCardException.class, topicQuiz::setUpQuiz);

    }

    @Test
    void setUpQuiz_TopicsWithFlashcards_checkMaxScore()
            throws NoFlashCardException {
        TopicQuiz topicQuiz = new TopicQuiz(topics.get(1));
        topicQuiz.setUpQuiz();
        assertEquals(topicQuiz.result.getMaxScore(), 2);

    }

}