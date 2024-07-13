package cft.intensive.potato.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "wallets")
@Getter
@Builder
@AllArgsConstructor
public class Wallet {

    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "wallets_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Integer id;

    private Integer balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addAmountOnBalance(int diff) {
        this.balance += diff;
    }

    public void subAmountFromBalance(int diff) {
        this.balance -= diff;
    }

    public Wallet() {}
}
