package kz.solva.solvatechoraz.model.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message, String placeMarkId) {
        super(placeMarkId + " " + message);
    }
}
