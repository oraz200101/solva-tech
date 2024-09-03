package kz.solva.solvatechoraz.controller;

import kz.solva.solvatechoraz.model.dto.AccountRequestDto;
import kz.solva.solvatechoraz.model.dto.AccountResponseDto;
import kz.solva.solvatechoraz.model.dto.LimitRequestDto;
import kz.solva.solvatechoraz.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        accountService.createAccount(accountRequestDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-limit")
    public ResponseEntity<?> changeLimit(@RequestParam("accountNumber") long accountNumber,
                                         @RequestBody LimitRequestDto limitRequestDto) {
        accountService.changeLimit(accountNumber, limitRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<AccountResponseDto> getAccount(@RequestParam("accountNumber") long accountNumber) {
        return ResponseEntity.ok(accountService.getByAccountNumber(accountNumber));
    }
}
