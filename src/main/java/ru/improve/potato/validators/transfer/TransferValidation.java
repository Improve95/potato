package ru.improve.potato.validators.transfer;

import ru.improve.potato.dto.transfer.TransferPostRequest;

public interface TransferValidation {

    boolean validateTransferCreateRequest(TransferPostRequest transferPostRequest);

    boolean isEnoughtMoneyForTransfer(TransferPostRequest transferPostRequest);
}
