package cft.intensive.potato.core.services.transfer;

import cft.intensive.potato.api.dto.transfer.TransferCreateRequest;

public interface TransferValidation {

    boolean validateTransferCreateRequest(TransferCreateRequest transferCreateRequest);

    boolean isEnoughtMoneyForTransfer(TransferCreateRequest transferCreateRequest);
}
