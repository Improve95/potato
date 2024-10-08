package ru.improve.potato.dto.transfer;

import ru.improve.potato.models.transfer.TransferType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Builder
@Getter
@Jacksonized
@ToString
public class TransferGetResponse {

    private final int id;

    private final TransferType transferType;

    private final int secondSideUserId;

    private final int secondSideWalletId;

    private final int amount;

    private final boolean status;

    private final LocalDateTime date;

    public boolean getStatus() {
        return this.status;
    }
}
