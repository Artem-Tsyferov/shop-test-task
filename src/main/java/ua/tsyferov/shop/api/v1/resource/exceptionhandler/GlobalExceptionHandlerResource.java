package ua.tsyferov.shop.api.v1.resource.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.tsyferov.shop.exception.ResourceNotFoundException;
import ua.tsyferov.shop.model.ApiErrorDto;
import ua.tsyferov.shop.service.DateTimeService;

import static ua.tsyferov.shop.util.WebUtil.getFullRequestUri;

@RestControllerAdvice
public class GlobalExceptionHandlerResource extends ResponseEntityExceptionHandler {

    private final DateTimeService dateTimeService;

    @Autowired
    public GlobalExceptionHandlerResource(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleResourceNotFoundException(ResourceNotFoundException exception) {

        ApiErrorDto error = createApiError(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorDto> handleIllegalArgumentException(IllegalArgumentException exception) {

        ApiErrorDto error = createApiError(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private ApiErrorDto createApiError(String message) {
        return new ApiErrorDto.ApiErrorDtoBuilder()
            .withTimestamp(dateTimeService.now())
            .withErrorMessage(message)
            .withPath(getFullRequestUri())
            .build();
    }
}
