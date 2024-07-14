package cft.intensive.potato.api.controllers;

import cft.intensive.potato.api.dto.transfer.TransferCreateRequest;
import cft.intensive.potato.api.dto.transfer.TransferCreateResponse;
import cft.intensive.potato.api.dto.transfer.TransferGetResponse;
import cft.intensive.potato.core.services.transfer.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/transfer")
@Slf4j
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public TransferCreateResponse createTransfer(@RequestBody TransferCreateRequest transferCreateRequest) {
        log.info("request arrived: " + transferCreateRequest.toString());
        return transferService.createTransfer(transferCreateRequest);
    }

    @GetMapping(path = "/{transferId}/wallet/{walletId}")
    public TransferGetResponse getTransferById(@PathVariable int transferId, int walletId) {
        log.info("request arrived: get transfer by id: {}", transferId);
        return transferService.getTransferById(transferId, walletId);
    }

    @GetMapping(path = "/wallet_id/{id}")
    public List<TransferGetResponse> getAllTransfersByWalletId(@PathVariable int id) {
        log.info("request arrived: get transfers by wallet id: {}", id);
        return transferService.getAllByWalletId(id);
    }
}
