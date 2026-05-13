package com.example.QuanLyHopDong.infrastructure.Persistence.Repositories;

import com.example.QuanLyHopDong.domain.Entity.KhachHang;
import com.example.QuanLyHopDong.infrastructure.Persistence.Entities.KhachHangjpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KhachHangjpaRepository extends JpaRepository<KhachHangjpaEntity,Long> {
    boolean existsByMaKhachHang(String maKhachHang);
}
