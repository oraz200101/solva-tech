package kz.solva.solvatechoraz.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CurrencyClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        return switch (status) {
            case NOT_FOUND ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Currency not found");
            case BAD_REQUEST ->
                    new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, change request and try again");
            case INTERNAL_SERVER_ERROR ->
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            default -> new Exception("Something went wrong, please check status" + status);
        };
    }
}
