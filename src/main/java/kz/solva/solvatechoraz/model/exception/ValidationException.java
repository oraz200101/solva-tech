package kz.solva.solvatechoraz.model.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String message, String placeMarkId) {
        super(placeMarkId + " " + message);
    }
}
