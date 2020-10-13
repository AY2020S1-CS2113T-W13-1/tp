package seedu.revised.exception;

public class FlashcardSyntaxException extends Exception {
    public FlashcardSyntaxException(String errorMessage) {
        super(errorMessage);
    }

    public FlashcardSyntaxException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
