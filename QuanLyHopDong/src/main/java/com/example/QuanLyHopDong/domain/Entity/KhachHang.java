package com.example.QuanLyHopDong.domain.Entity;

public class KhachHang {
    private Long id;
    private String maKhachHang;
    private String tenKhachHang;
    private String email;
    private String soDienThoai;


    public KhachHang(String maKhachHang, String tenKhachHang, String email, String soDienThoai) {
        if (tenKhachHang == null || tenKhachHang.isEmpty()) {
            throw new IllegalArgumentException("Tên khách hàng không được để trống");
        }

        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.email = email;
        this.soDienThoai = soDienThoai;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaKhachHang() { return maKhachHang; }
    public String getTenKhachHang() { return tenKhachHang; }
    public String getEmail() { return email; }
    public String getSoDienThoai() { return soDienThoai; }
}
