package ru.improve.potato.core.validators.transfer;

import ru.improve.potato.api.dto.transfer.TransferPostRequest;

public interface TransferValidation {

    boolean validateTransferCreateRequest(TransferPostRequest transferPostRequest);

    boolean isEnoughtMoneyForTransfer(TransferPostRequest transferPostRequest);
}
