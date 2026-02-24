package org.ahmetsezer.stockwise.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ahmetsezer.stockwise.dto.request.SupplierRequest;
import org.ahmetsezer.stockwise.dto.response.SupplierResponse;
import org.ahmetsezer.stockwise.entity.Supplier;
import org.ahmetsezer.stockwise.exception.ResourceNotFoundException;
import org.ahmetsezer.stockwise.repository.SupplierRepository;
import org.ahmetsezer.stockwise.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ahmetsezer.stockwise.exception.EmailAlreadyExistsException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public Page<SupplierResponse> getAllSuppliers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return supplierRepository.findAll(pageable)
                .map(supplier -> modelMapper.map(supplier, SupplierResponse.class));
    }

    public Page<SupplierResponse> getActiveSuppliers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Supplier> supplierPage = supplierRepository.findAllByActiveTrue(pageable);

        return supplierPage.map(supplier -> modelMapper.map(supplier, SupplierResponse.class));
    }

    public SupplierResponse createSupplier(SupplierRequest supplierRequest) {

        if (supplierRepository.existsByEmail(supplierRequest.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "This email address (" + supplierRequest.getEmail() + ") is already in use!"
            );
        }

        Supplier supplier = modelMapper.map(supplierRequest, Supplier.class);

        // Default business rule
        supplier.setActive(true);

        Supplier savedSupplier = supplierRepository.save(supplier);

        return modelMapper.map(savedSupplier, SupplierResponse.class);
    }

    public void deleteSupplierById(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found!"));

        supplier.setActive(false);

        supplierRepository.save(supplier);
    }

    public SupplierResponse updateSupplier(Long id, SupplierRequest request) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Supplier to be updated was not found!"
                ));

        Optional<Supplier> existingSupplier =
                supplierRepository.findByEmail(request.getEmail());

        if (existingSupplier.isPresent()
                && !existingSupplier.get().getId().equals(id)) {

            throw new EmailAlreadyExistsException(
                    "This email is already used by another supplier!"
            );
        }

        supplier.setName(request.getName());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return modelMapper.map(updatedSupplier, SupplierResponse.class);
    }
}