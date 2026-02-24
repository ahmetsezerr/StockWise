package org.ahmetsezer.stockwise.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.request.SupplierRequest;
import org.ahmetsezer.stockwise.dto.response.SupplierResponse;
import org.ahmetsezer.stockwise.service.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<Page<SupplierResponse>> getAllSuppliers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)

     {
        return ResponseEntity.ok(supplierService.getAllSuppliers(page,size));
    }

    @GetMapping("/active")
    public ResponseEntity<Page<SupplierResponse>> getActiveSuppliers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(supplierService.getActiveSuppliers(page, size));
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> createSuppliers(@Valid @RequestBody SupplierRequest request) {

        SupplierResponse createdSupplier = supplierService.createSupplier(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> updateSupplier(@PathVariable  Long id,@Valid @RequestBody SupplierRequest request){

        SupplierResponse updatedSupplier = supplierService.updateSupplier(id,request);

        return ResponseEntity.ok(updatedSupplier);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {

        supplierService.deleteSupplierById(id);
        return ResponseEntity.noContent().build();
    }
}

