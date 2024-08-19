package cft.intensive.potato.core.services.wallet;

import cft.intensive.potato.api.dto.wallet.WalletGetResponse;

public interface WalletService {

    WalletGetResponse getById(int id);
}
