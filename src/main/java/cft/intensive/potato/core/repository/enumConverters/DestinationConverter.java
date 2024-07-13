package cft.intensive.potato.core.repository.enumConverters;

import cft.intensive.potato.model.transfer.TransferDestination;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class DestinationConverter implements AttributeConverter<TransferDestination, String> {

    @Override
    public String convertToDatabaseColumn(TransferDestination transferDestination) {
        if (transferDestination == null) {
            return null;
        }
        return transferDestination.getCode();
    }

    @Override
    public TransferDestination convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(TransferDestination.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
