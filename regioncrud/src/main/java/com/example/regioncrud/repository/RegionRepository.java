package com.example.regioncrud.repository;

import com.example.regioncrud.model.Region; // Pastikan import model Region
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Menandakan ini adalah Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    // Biarkan KOSONG
}