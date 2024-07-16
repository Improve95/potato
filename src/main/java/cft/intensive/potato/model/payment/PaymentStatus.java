package cft.intensive.potato.model.payment;

public enum PaymentStatus {
    PAID("PAID"), UNPAID("UNPAID"), CANCELED("CANCELED");

    private final String code;

    PaymentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
