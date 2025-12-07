package uk.gov.hmcts.reform.dev.exceptions;

public class NoSuchTaskExistsException extends RuntimeException {

    private String message;

    public NoSuchTaskExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
