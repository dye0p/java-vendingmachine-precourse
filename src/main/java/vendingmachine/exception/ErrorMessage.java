package vendingmachine.exception;

public enum ErrorMessage {

    ERROR_SIGNATURE("[ERROR] "),
    INVALID_AMOUNT("금액은 숫자여야 합니다."),
    DIVIDE_AMOUNT("금액은 10원 단위여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return ERROR_SIGNATURE.message + this.message;
    }
}
