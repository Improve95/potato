package cft.intensive.potato.core.repository.enumConverters;

import cft.intensive.potato.model.payment.PaymentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {

    @Override
    public String convertToDatabaseColumn(PaymentStatus attribute) {
        if (attribute == null) return null;

        return attribute.getCode();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String code) {
        if (code == null) return null;

        return Stream.of(PaymentStatus.values())
                .filter(paymentStatus -> paymentStatus.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
