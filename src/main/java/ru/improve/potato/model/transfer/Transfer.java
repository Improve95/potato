package ru.improve.potato.model.transfer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
@Builder
@AllArgsConstructor
@Getter
public class Transfer {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "transfers_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private int id;

    @Column(name = "sender_wallet_id")
    private int senderWalletId;

    @Column(name = "transfer_destination")
    private TransferDestination destination;

    @Column(name = "user_transfer_way")
    private UserTransferWay userTransferWay;

    @Column(name = "receiver_wallet_id")
    private int receiverWalletId;

    @Column(name = "service_id")
    private int serviceId;

    private int amount;

    private boolean status;

    private LocalDateTime date;

    public Transfer() {}

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
