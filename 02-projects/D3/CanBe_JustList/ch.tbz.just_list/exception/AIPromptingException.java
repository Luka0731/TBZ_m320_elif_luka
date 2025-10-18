package exception;

public class AIPromptingException extends RuntimeException {
    public AIPromptingException() {
        super("Failed to get answer of AI");
    }
}
