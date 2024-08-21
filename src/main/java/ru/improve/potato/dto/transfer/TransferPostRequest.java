package ru.improve.potato.dto.transfer;

import ru.improve.potato.models.transfer.TransferDestination;
import ru.improve.potato.models.transfer.UserTransferWay;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
@ToString
public class TransferPostRequest {

    private final int senderWalletId;

    private final TransferDestination destination;

    private final UserTransferWay userTransferWay;

    private final int receiverWalletId;

    private final String receiverPhone;

    private final int serviceId;

    private final int amount;
}
