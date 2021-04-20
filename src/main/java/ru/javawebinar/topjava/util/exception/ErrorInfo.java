package ru.javawebinar.topjava.util.exception;

public class ErrorInfo {
    private final String url;
    private final String errorMessage;
    private final String detail;

    public ErrorInfo(CharSequence url, String errorMessage, String detail) {
        this.url = url.toString();
        this.errorMessage = errorMessage;
        this.detail = detail;
    }
}