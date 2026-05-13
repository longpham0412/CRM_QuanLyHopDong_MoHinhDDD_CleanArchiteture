package com.example.QuanLyHopDong.application.Usecase;

import com.example.QuanLyHopDong.application.Commad.TaoKhachHangCommad;
import com.example.QuanLyHopDong.domain.Entity.KhachHang;
import com.example.QuanLyHopDong.domain.Repositoryinterface.IKhachHangReposity;
import org.springframework.stereotype.Service;

@Service
public class TaoKhachHangUseCase {
    private final IKhachHangReposity repository;

    public TaoKhachHangUseCase(IKhachHangReposity repository) { this.repository = repository; }

    public Long execute(TaoKhachHangCommad commad) {
        KhachHang kh = new KhachHang(commad.maKhachHang(), commad.tenKhachHang(), commad.email(), commad.soDienThoai());
        return repository.save(kh).getId();
    }
}
