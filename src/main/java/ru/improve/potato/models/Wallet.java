package ru.improve.potato.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wallets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet extends BaseEntity {

    private Integer balance;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    public void addAmountOnBalance(int diff) {
        this.balance += diff;
    }

    public void subAmountFromBalance(int diff) {
        this.balance -= diff;
    }
}
