package fr.softquipeut.pocvertx.exceptions;

/**
 *
 * @author Joce
 */
public class ClientReadableException extends RuntimeException {

    private int errorCode = 500;
    
    public ClientReadableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ClientReadableException(Throwable cause) {
        super(cause);
    }

    public ClientReadableException(String message, Throwable cause) {
        super(message, cause);
    }

    private ClientReadableException(String message, int error) {
        super(message);
        errorCode = error;
    }

    public int getErrorCode() {
        return errorCode;
    }
    
    
    
    public static ClientReadableException newNotFound(String message){
        return new ClientReadableException(message, 404);
    }
       
    
}
