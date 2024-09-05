package kz.solva.solvatechoraz.controller;

import kz.solva.solvatechoraz.model.dto.TransactionFilterRequestDto;
import kz.solva.solvatechoraz.model.dto.TransactionRequestDto;
import kz.solva.solvatechoraz.model.dto.TransactionResponseDto;
import kz.solva.solvatechoraz.model.dto.TransactionResponseTableDto;
import kz.solva.solvatechoraz.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody TransactionRequestDto requestDto) {
        return ResponseEntity.ok(transactionService.createTransaction(requestDto));
    }

    @GetMapping("/get")
    public ResponseEntity<TransactionResponseTableDto> getTransactionByFilter(@RequestBody TransactionFilterRequestDto filterRequest) {
        return ResponseEntity.ok(transactionService.getTransactionsByFilter(filterRequest));
    }

}
