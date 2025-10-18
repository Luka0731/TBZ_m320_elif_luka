package exception;

import java.io.IOException;

public class DataSaveException extends RuntimeException {
  public DataSaveException(String message, IOException ioException) {
    super(message, ioException );
  }
}
