package ru.improve.potato.core.services.wallet;

import ru.improve.potato.api.dto.wallet.WalletGetResponse;
import ru.improve.potato.core.exceptions.transfer.NotFoundException;
import ru.improve.potato.core.repository.WalletRepository;
import ru.improve.potato.model.Wallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletServiceImp implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public WalletGetResponse getById(int id) {

        Wallet wallet = Optional.ofNullable(walletRepository.getById(id))
                .orElseThrow(() -> new NotFoundException("wallet not found"));

        return WalletGetResponse.builder()
                .id(wallet.getId())
                .balance(wallet.getBalance())
                .userId(wallet.getUser().getId())
                .build();
    }
}
