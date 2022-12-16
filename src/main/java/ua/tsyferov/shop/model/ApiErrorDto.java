package ua.tsyferov.shop.model;

import java.time.LocalDateTime;

public class ApiErrorDto {

    private LocalDateTime timestamp;

    private String errorMessage;

    private String path;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ApiErrorDto{" +
            "timestamp=" + timestamp +
            ", errorMessage='" + errorMessage + '\'' +
            ", path='" + path + '\'' +
            '}';
    }

    public static final class ApiErrorDtoBuilder {
        private LocalDateTime timestamp;
        private String errorMessage;
        private String path;

        public ApiErrorDtoBuilder() {
        }

        public static ApiErrorDtoBuilder anApiErrorDto() {
            return new ApiErrorDtoBuilder();
        }

        public ApiErrorDtoBuilder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ApiErrorDtoBuilder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ApiErrorDtoBuilder withPath(String path) {
            this.path = path;
            return this;
        }

        public ApiErrorDto build() {
            ApiErrorDto apiErrorDto = new ApiErrorDto();
            apiErrorDto.setTimestamp(timestamp);
            apiErrorDto.setErrorMessage(errorMessage);
            apiErrorDto.setPath(path);
            return apiErrorDto;
        }
    }
}
