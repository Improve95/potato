package ru.improve.potato.services.transfer;

import ru.improve.potato.dto.transfer.TransferGetResponse;
import ru.improve.potato.dto.transfer.TransferPostRequest;
import ru.improve.potato.dto.transfer.TransferPostResponse;
import ru.improve.potato.models.transfer.TransferType;

import java.util.List;

public interface TransferService {

    TransferPostResponse createTransfer(TransferPostRequest transfer);

    TransferGetResponse getTransferById(int transferId, int walletId);

    List<TransferGetResponse> getAllTransfersByWalletId(int walletId);

    List<TransferGetResponse> getAllTransfersByWalletIdAndParameters(int walletId, TransferType transferType, Boolean transferStatus);
}
