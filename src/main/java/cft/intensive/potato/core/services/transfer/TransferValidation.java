package cft.intensive.potato.core.services.transfer;

import cft.intensive.potato.api.dto.transfer.TransferPostRequest;

public interface TransferValidation {

    boolean validateTransferCreateRequest(TransferPostRequest transferPostRequest);

    boolean isEnoughtMoneyForTransfer(TransferPostRequest transferPostRequest);
}
