package com.example.QuanLyHopDong.infrastructure.Persistence.Repositories;

import com.example.QuanLyHopDong.application.Commad.TaoKhachHangCommad;
import com.example.QuanLyHopDong.application.Usecase.LayDanhSachKhachHangUseCase;
import com.example.QuanLyHopDong.application.Usecase.TaoKhachHangUseCase;
import com.example.QuanLyHopDong.domain.Entity.KhachHang;
import com.example.QuanLyHopDong.domain.Repositoryinterface.IKhachHangReposity;
import com.example.QuanLyHopDong.infrastructure.Persistence.Entities.KhachHangjpaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KhachHangRepository implements IKhachHangReposity {
    private final com.example.QuanLyHopDong.infrastructure.Persistence.Repositories.KhachHangjpaRepository jpaRepository;

    public KhachHangRepository(com.example.QuanLyHopDong.infrastructure.Persistence.Repositories.KhachHangjpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public KhachHang save(KhachHang khachHang) {

        KhachHangjpaEntity jpaEntity = new KhachHangjpaEntity();
        jpaEntity.setMaKhachHang(khachHang.getMaKhachHang());
        jpaEntity.setTenKhachHang(khachHang.getTenKhachHang());
        jpaEntity.setEmail(khachHang.getEmail());
        jpaEntity.setSoDienThoai(khachHang.getSoDienThoai());
        KhachHangjpaEntity savedEntity = jpaRepository.save(jpaEntity);

        khachHang.setId(savedEntity.getId());
        return khachHang;
    }

    @Override
    public List<KhachHang> findAll() {
        return jpaRepository.findAll().stream().map(jpaEntity -> {
            KhachHang domain = new KhachHang(
                    jpaEntity.getMaKhachHang(),
                    jpaEntity.getTenKhachHang(),
                    jpaEntity.getEmail(),
                    jpaEntity.getSoDienThoai()
            );
            domain.setId(jpaEntity.getId());
            return domain;
        }).collect(Collectors.toList());
    }

}
