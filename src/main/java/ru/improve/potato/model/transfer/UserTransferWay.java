package ru.improve.potato.model.transfer;

public enum UserTransferWay {
    BY_ID("BY_ID"), BY_TELEPHONE("BY_TELEPHONE");

    private String code;

    UserTransferWay(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
