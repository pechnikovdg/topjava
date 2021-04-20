package ru.javawebinar.topjava.util.exception;

public enum ErrorType {
    APP_ERROR("App error"),
    DATA_NOT_FOUND("Data not found"),
    DATA_ERROR("Data error"),
    VALIDATION_ERROR("Validation error");

    private final String errorTypeName;

    ErrorType(String errorTypeName) {
        this.errorTypeName = errorTypeName;
    }

    public String getErrorTypeName() {
        return errorTypeName;
    }
}
