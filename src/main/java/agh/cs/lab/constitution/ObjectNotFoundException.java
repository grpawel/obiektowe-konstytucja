package agh.cs.lab.constitution;

/**
 * Created by Paweł Grochola on 09.12.2016.
 */
public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
