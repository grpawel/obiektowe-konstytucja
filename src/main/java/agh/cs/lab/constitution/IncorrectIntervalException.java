package agh.cs.lab.constitution;

/**
 * Created by Paweł Grochola on 16.12.2016.
 */
public class IncorrectIntervalException extends Exception {
    public IncorrectIntervalException() {
    }

    public IncorrectIntervalException(String message) {
        super(message);
    }

    public IncorrectIntervalException(Throwable cause) {
        super(cause);
    }

    public IncorrectIntervalException(String message, Throwable cause) {
        super(message, cause);
    }
}
