package org.ahmetsezer.stockwise.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.request.PurchaseRequest;
import org.ahmetsezer.stockwise.dto.response.PurchaseResponse;
import org.ahmetsezer.stockwise.service.PurchaseService;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<Page<PurchaseResponse>> getAllPurchases(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(purchaseService.getAllPurchases(page,size));
    }

    @PostMapping
    public ResponseEntity<PurchaseResponse> create(@Valid @RequestBody PurchaseRequest request) {

        return ResponseEntity.ok(purchaseService.createPurchase(request));
    }
}
