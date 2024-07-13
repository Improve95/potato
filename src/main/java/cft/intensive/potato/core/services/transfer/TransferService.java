package cft.intensive.potato.core.services.transfer;

import cft.intensive.potato.api.dto.transfer.TransferCreateRequest;
import cft.intensive.potato.api.dto.transfer.TransferCreateResponse;
import cft.intensive.potato.api.dto.transfer.TransferGetResponse;
import cft.intensive.potato.core.exceptions.transfer.NotFoundException;

import java.util.List;

public interface TransferService {

    TransferCreateResponse createTransfer(TransferCreateRequest transfer) throws NotFoundException;

    List<TransferGetResponse> getAllByUserId(int id);
}
