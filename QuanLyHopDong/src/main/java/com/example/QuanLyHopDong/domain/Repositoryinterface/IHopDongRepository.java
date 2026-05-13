package com.example.QuanLyHopDong.domain.Repositoryinterface;

import com.example.QuanLyHopDong.domain.Entity.HopDong;

import java.util.List;
import java.util.Optional;

public interface IHopDongRepository {
    HopDong save(HopDong hopDong);
    boolean existsByMaHopDong(String maHopDong);
    List<HopDong> findAll();
    Optional<HopDong> findById(Long id);
    void deleteById(Long id);
}
