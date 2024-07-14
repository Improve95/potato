package cft.intensive.potato.core.services.transfer;

import cft.intensive.potato.api.dto.transfer.TransferCreateRequest;
import cft.intensive.potato.api.dto.transfer.TransferCreateResponse;
import cft.intensive.potato.api.dto.transfer.TransferGetResponse;
import cft.intensive.potato.core.exceptions.transfer.NotFoundException;
import cft.intensive.potato.model.transfer.TransferType;

import java.util.List;

public interface TransferService {

    TransferCreateResponse createTransfer(TransferCreateRequest transfer) throws NotFoundException;

    TransferGetResponse getTransferById(int transferId, int walletId);

    List<TransferGetResponse> getAllTransfersByWalletId(int walletId);

    List<TransferGetResponse> getAllTransfersByWalletIdAndParameters(int walletId, TransferType transferType, Boolean transferStatus);
}
