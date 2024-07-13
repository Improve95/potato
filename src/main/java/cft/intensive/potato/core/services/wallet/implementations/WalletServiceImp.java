package cft.intensive.potato.core.services.wallet.implementations;

import cft.intensive.potato.api.dto.wallet.WalletGetResponse;
import cft.intensive.potato.core.exceptions.transfer.NotFoundException;
import cft.intensive.potato.core.repository.WalletRepository;
import cft.intensive.potato.core.services.wallet.WalletService;
import cft.intensive.potato.model.Wallet;
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
                .orElseThrow(() -> new NotFoundException("wallet not found", 404));

        return WalletGetResponse.builder()
                .id(wallet.getId())
                .balance(wallet.getBalance())
                .userId(wallet.getUser().getId())
                .build();
    }
}
