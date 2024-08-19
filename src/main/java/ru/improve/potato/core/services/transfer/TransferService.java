package ru.improve.potato.core.services.transfer;

import ru.improve.potato.api.dto.transfer.TransferPostRequest;
import ru.improve.potato.api.dto.transfer.TransferPostResponse;
import ru.improve.potato.api.dto.transfer.TransferGetResponse;
import ru.improve.potato.core.exceptions.transfer.NotFoundException;
import ru.improve.potato.model.transfer.TransferType;

import java.util.List;

public interface TransferService {

    TransferPostResponse createTransfer(TransferPostRequest transfer) throws NotFoundException;

    TransferGetResponse getTransferById(int transferId, int walletId);

    List<TransferGetResponse> getAllTransfersByWalletId(int walletId);

    List<TransferGetResponse> getAllTransfersByWalletIdAndParameters(int walletId, TransferType transferType, Boolean transferStatus);
}
