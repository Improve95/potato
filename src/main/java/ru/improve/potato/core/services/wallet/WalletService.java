package ru.improve.potato.core.services.wallet;

import ru.improve.potato.api.dto.wallet.WalletGetResponse;

public interface WalletService {

    WalletGetResponse getById(int id);
}
