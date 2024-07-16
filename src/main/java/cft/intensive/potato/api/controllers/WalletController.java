package cft.intensive.potato.api.controllers;

import cft.intensive.potato.api.dto.user.wallet.WalletGetResponse;
import cft.intensive.potato.core.services.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
@Slf4j
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/{id}")
    public WalletGetResponse getWalletById(@PathVariable int id) {
        log.info("get wallet by id: {}", id);
        return walletService.getById(id);
    }
}
