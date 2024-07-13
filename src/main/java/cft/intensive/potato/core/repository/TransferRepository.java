package cft.intensive.potato.core.repository;

import cft.intensive.potato.model.transfer.Transfer;

public interface TransferRepository {

    boolean transactionIsExist(int id);

    Transfer getById(int id);

    int addTransfer(Transfer transfer);

    void changeTransferStatus(int id, boolean status);
}
