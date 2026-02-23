package org.ahmetsezer.stockwise.repository;

import org.ahmetsezer.stockwise.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {


    boolean existsByEmail(String email);

    Optional<Supplier> findByEmail(String email);

    Page<Supplier> findAllByActiveTrue(Pageable pageable);
}

