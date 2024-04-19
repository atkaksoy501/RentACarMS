package com.turkcell.rentacar.inventoryService.dataAccess.abstracts;

import com.turkcell.rentacar.inventoryService.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Optional<Brand> findByNameIgnoreCase(String brandName);
}