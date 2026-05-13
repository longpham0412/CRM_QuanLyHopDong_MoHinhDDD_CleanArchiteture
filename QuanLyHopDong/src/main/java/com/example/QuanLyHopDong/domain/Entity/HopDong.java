package com.example.QuanLyHopDong.domain.Entity;

import com.example.QuanLyHopDong.domain.Ennum.TrangThaiHopDong;

import java.time.LocalDate;

public class HopDong {
    private Long id;
    private String maHopDong;
    private Long khachHangId;
    private LocalDate ngayKy;
    private Integer thoiHan;
    private TrangThaiHopDong trangThai;

    public HopDong(String maHopDong, Long khachHangId, LocalDate ngayKy, Integer thoiHan) {
        if (thoiHan <= 0) throw new IllegalArgumentException("Thời hạn phải lớn hơn 0");

        this.maHopDong = maHopDong;
        this.khachHangId = khachHangId;
        this.ngayKy = ngayKy;
        this.thoiHan = thoiHan;
        this.trangThai = TrangThaiHopDong.DangThucHien; // Trạng thái mặc định
    }


    public Long getId() { return id; }
    public String getMaHopDong() { return maHopDong; }
    public Long getKhachHangId() { return khachHangId; }
    public TrangThaiHopDong getTrangThai() { return trangThai; }
    public LocalDate getNgayKy() { return ngayKy; }
    public Integer getThoiHan() { return thoiHan; }
    public void setId(Long id) { this.id = id; }
    public void thanhLy() {
        if (this.trangThai == TrangThaiHopDong.ThanhLy) {
            throw new IllegalStateException("Hợp đồng đã được thanh lý trước đó.");
        }
        this.trangThai = TrangThaiHopDong.ThanhLy;
    }

    public void capNhatThongTin(String maHopDong, Long khachHangId, LocalDate ngayKy, Integer thoiHan) {

        if (thoiHan <= 0) throw new IllegalArgumentException("Thời hạn phải lớn hơn 0");
        this.maHopDong = maHopDong;
        this.khachHangId = khachHangId;
        this.ngayKy = ngayKy;
        this.thoiHan = thoiHan;
    }
}
