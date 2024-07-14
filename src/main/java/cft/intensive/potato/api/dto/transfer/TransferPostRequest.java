package cft.intensive.potato.api.dto.transfer;

import cft.intensive.potato.model.transfer.TransferDestination;
import cft.intensive.potato.model.transfer.UserTransferWay;
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
