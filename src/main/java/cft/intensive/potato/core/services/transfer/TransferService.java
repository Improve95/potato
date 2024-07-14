package cft.intensive.potato.core.services.transfer;

import cft.intensive.potato.api.dto.transfer.TransferPostRequest;
import cft.intensive.potato.api.dto.transfer.TransferPostResponse;
import cft.intensive.potato.api.dto.transfer.TransferGetResponse;
import cft.intensive.potato.core.exceptions.transfer.NotFoundException;
import cft.intensive.potato.model.transfer.TransferType;

import java.util.List;

public interface TransferService {

    TransferPostResponse createTransfer(TransferPostRequest transfer) throws NotFoundException;

    TransferGetResponse getTransferById(int transferId, int walletId);

    List<TransferGetResponse> getAllTransfersByWalletId(int walletId);

    List<TransferGetResponse> getAllTransfersByWalletIdAndParameters(int walletId, TransferType transferType, Boolean transferStatus);
}
