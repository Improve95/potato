package ru.improve.potato.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
