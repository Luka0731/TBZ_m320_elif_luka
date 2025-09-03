package ch.tbz.budgedbuddy.exception;

import java.io.IOException;

public class StorageException extends RuntimeException {
    public StorageException(String message, IOException ioException) { super(message, ioException); }
}
