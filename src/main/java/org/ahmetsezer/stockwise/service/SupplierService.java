package org.ahmetsezer.stockwise.service;

import org.ahmetsezer.stockwise.dto.request.SupplierRequest;
import org.ahmetsezer.stockwise.dto.response.SupplierResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {

    Page<SupplierResponse> getAllSuppliers(int page, int size);

    Page<SupplierResponse> getActiveSuppliers(int page, int size);

    SupplierResponse createSupplier(SupplierRequest request);

    SupplierResponse updateSupplier(Long id, SupplierRequest request);

    void deleteSupplierById(Long id);
}
