package org.ahmetsezer.stockwise.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.request.PurchaseRequest;
import org.ahmetsezer.stockwise.dto.response.PurchaseResponse;
import org.ahmetsezer.stockwise.entity.Product;
import org.ahmetsezer.stockwise.entity.Purchase;
import org.ahmetsezer.stockwise.entity.Supplier;
import org.ahmetsezer.stockwise.exception.ResourceNotFoundException;
import org.ahmetsezer.stockwise.exception.SupplierNotActiveException;
import org.ahmetsezer.stockwise.repository.ProductRepository;
import org.ahmetsezer.stockwise.repository.PurchaseRepository;
import org.ahmetsezer.stockwise.repository.SupplierRepository;
import org.ahmetsezer.stockwise.service.PurchaseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<PurchaseResponse> getAllPurchases(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return purchaseRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    private PurchaseResponse mapToResponse(Purchase purchase) {

        PurchaseResponse response =
                modelMapper.map(purchase, PurchaseResponse.class);

        response.setProductId(purchase.getProduct().getId());
        response.setProductName(purchase.getProduct().getName());

        response.setSupplierId(purchase.getSupplier().getId());
        response.setSupplierName(purchase.getSupplier().getName());

        response.setTotalAmount(
                purchase.getUnitPrice()
                        .multiply(BigDecimal.valueOf(purchase.getQuantity()))
        );

        return response;
    }

    @Override
    public PurchaseResponse createPurchase(PurchaseRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + request.getProductId()));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Supplier not found with id: " + request.getSupplierId()));

        if (!supplier.isActive()) {
            throw new SupplierNotActiveException(
                    "Supplier is not active with id: " + supplier.getId());
        }

        product.setStockQuantity(
                product.getStockQuantity() + request.getQuantity()
        );

        productRepository.save(product);

        Purchase purchase = Purchase.builder()
                .product(product)
                .supplier(supplier)
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .purchaseDate(LocalDateTime.now())
                .build();

        Purchase savedPurchase = purchaseRepository.save(purchase);

        return mapToResponse(savedPurchase);
    }
}