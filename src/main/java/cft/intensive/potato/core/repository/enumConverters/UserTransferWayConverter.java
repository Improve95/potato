package cft.intensive.potato.core.repository.enumConverters;

import cft.intensive.potato.model.transfer.UserTransferWay;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserTransferWayConverter implements AttributeConverter<UserTransferWay, String> {

    @Override
    public String convertToDatabaseColumn(UserTransferWay userTransferWay) {
        if (userTransferWay == null) {
            return null;
        }
        return userTransferWay.getCode();
    }

    @Override
    public UserTransferWay convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(UserTransferWay.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
