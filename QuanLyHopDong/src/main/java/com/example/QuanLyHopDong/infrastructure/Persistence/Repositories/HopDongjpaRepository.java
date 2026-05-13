package com.example.QuanLyHopDong.infrastructure.Persistence.Repositories;

import com.example.QuanLyHopDong.infrastructure.Persistence.Entities.HopDongjpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HopDongjpaRepository extends JpaRepository<HopDongjpaEntity, Long> {
    boolean existsByMaHopDong(String maHopDong);

}
