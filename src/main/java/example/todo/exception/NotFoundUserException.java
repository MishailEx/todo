package example.todo.exception;

public class NotFoundUserException extends Exception {
    public NotFoundUserException(String message) {
        super(message);
    }
}
