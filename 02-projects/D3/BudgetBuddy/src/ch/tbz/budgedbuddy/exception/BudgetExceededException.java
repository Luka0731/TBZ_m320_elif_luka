package ch.tbz.budgedbuddy.exception;

public class BudgetExceededException extends RuntimeException {
    public BudgetExceededException(String message) {
        super(message);
    }
}
