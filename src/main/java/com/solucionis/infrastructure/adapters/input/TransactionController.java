package com.solucionis.infrastructure.adapters.input;

import com.solucionis.application.service.TransferService;
import com.solucionis.domain.models.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
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
     * Classe para representar a requisição de transferência.
     */
    public static class TransferRequest {
        private UUID payerId;
        private UUID payeeId;
        private BigDecimal amount;

        // Construtor vazio (necessário para serialização JSON)
        public TransferRequest() {
        }

        // Construtor completo (opcional para facilitar testes e inicializações manuais)
        public TransferRequest(UUID payerId, UUID payeeId, BigDecimal amount) {
            this.payerId = payerId;
            this.payeeId = payeeId;
            this.amount = amount;
        }

        // Getters e Setters
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
