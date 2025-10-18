package exception;

import java.io.IOException;

public class LoadingException extends RuntimeException {
public LoadingException(String message, IOException ioException) {
        super(message, ioException );
    }
}
