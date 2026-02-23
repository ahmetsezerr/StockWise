package org.ahmetsezer.stockwise.service;

import org.ahmetsezer.stockwise.dto.request.PurchaseRequest;
import org.ahmetsezer.stockwise.dto.request.SupplierRequest;
import org.ahmetsezer.stockwise.dto.response.PurchaseResponse;
import org.ahmetsezer.stockwise.dto.response.SupplierResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {

    Page<PurchaseResponse> getAllPurchases(int page, int size);

    PurchaseResponse createPurchase(PurchaseRequest request);


}
