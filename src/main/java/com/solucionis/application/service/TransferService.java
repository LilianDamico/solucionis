package com.solucionis.application.service;

import com.solucionis.domain.models.Transaction;
import com.solucionis.domain.models.TransactionStatus;
import com.solucionis.domain.models.Wallet;
import com.solucionis.infrastructure.persistence.repositories.TransactionRepository;
import com.solucionis.infrastructure.persistence.repositories.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransferService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public TransferService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction transfer(UUID payerId, UUID payeeId, BigDecimal amount) {
        Wallet payerWallet = walletRepository.findByUserId(payerId)
                .orElseThrow(() -> new IllegalArgumentException("Payer not found"));
        Wallet payeeWallet = walletRepository.findByUserId(payeeId)
                .orElseThrow(() -> new IllegalArgumentException("Payee not found"));

        if (payerWallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        payerWallet.withdraw(amount);
        payeeWallet.deposit(amount);
        walletRepository.save(payerWallet);
        walletRepository.save(payeeWallet);

        Transaction transaction = new Transaction();
        transaction.setPayer(payerWallet.getUser());
        transaction.setPayee(payeeWallet.getUser());
        transaction.setAmount(amount);
        transaction.setStatus(TransactionStatus.COMPLETED);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
