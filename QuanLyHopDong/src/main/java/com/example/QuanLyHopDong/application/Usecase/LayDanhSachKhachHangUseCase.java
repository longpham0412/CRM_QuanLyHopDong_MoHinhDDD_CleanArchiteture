package com.example.QuanLyHopDong.application.Usecase;

import com.example.QuanLyHopDong.domain.Entity.HopDong;
import com.example.QuanLyHopDong.domain.Entity.KhachHang;
import com.example.QuanLyHopDong.domain.Repositoryinterface.IHopDongRepository;
import com.example.QuanLyHopDong.domain.Repositoryinterface.IKhachHangReposity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LayDanhSachKhachHangUseCase {
    private final IKhachHangReposity hopDongRepository;

    public LayDanhSachKhachHangUseCase(IKhachHangReposity hopDongRepository) {
        this.hopDongRepository = hopDongRepository;
    }

    public List<KhachHang> execute() {
        return hopDongRepository.findAll();
    }
}
