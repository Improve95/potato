package ru.improve.potato.models.transfer;

public enum TransferType {
    SEND("SEND"), RECEIVE("RECEIVE");

    private String code;

    TransferType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
