package com.example.QuanLyHopDong.domain.Repositoryinterface;

import com.example.QuanLyHopDong.domain.Entity.KhachHang;

import java.util.List;

public interface IKhachHangReposity {
    KhachHang save(KhachHang khachHang);
    List<KhachHang> findAll();
}
