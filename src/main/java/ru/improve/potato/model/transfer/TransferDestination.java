package ru.improve.potato.model.transfer;

public enum TransferDestination {
    USER("USER"), SERVICE("SERVICE");

    private String code;

    TransferDestination(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
