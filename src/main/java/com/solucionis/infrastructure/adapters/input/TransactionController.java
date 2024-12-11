package com.solucionis.infrastructure.adapters.input;

import com.solucionis.application.service.TransferService;
import com.solucionis.domain.models.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransferService transferService;

    public TransactionController(TransferService transferService) {
        this.transferService = transferService;
    }

    /**
     * Endpoint para criar uma nova transação.
     * 
     * @param request Dados da transação (payerId, payeeId e amount).
     * @return Informações da transação criada.
     */
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransferRequest request) {
        Transaction transaction = transferService.transfer(
                request.getPayerId(),
                request.getPayeeId(),
                request.getAmount()
        );
        return ResponseEntity.ok(transaction);
    }

    /**
     * Endpoint para listar todas as transações.
     * 
     * @return Lista de transações existentes.
     */
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transferService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    /**
     * Classe interna para representar a requisição de transferência.
     */
    public static class TransferRequest {
        private UUID payerId;
        private UUID payeeId;
        private BigDecimal amount;

        public TransferRequest() {
        }

        public TransferRequest(UUID payerId, UUID payeeId, BigDecimal amount) {
            this.payerId = payerId;
            this.payeeId = payeeId;
            this.amount = amount;
        }

        public UUID getPayerId() {
            return payerId;
        }

        public void setPayerId(UUID payerId) {
            this.payerId = payerId;
        }

        public UUID getPayeeId() {
            return payeeId;
        }

        public void setPayeeId(UUID payeeId) {
            this.payeeId = payeeId;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }
}
