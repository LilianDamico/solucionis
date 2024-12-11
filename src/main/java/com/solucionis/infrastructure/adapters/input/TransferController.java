package com.solucionis.infrastructure.adapters.input;

import com.solucionis.application.service.TransferService;
import com.solucionis.domain.models.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Transaction> transfer(@RequestBody TransferRequest request) {
        Transaction transaction = transferService.transfer(request.getPayerId(), request.getPayeeId(), request.getAmount());
        return ResponseEntity.ok(transaction);
    }

    public static class TransferRequest {
        private UUID payerId;
        private UUID payeeId;
        private BigDecimal amount;

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
