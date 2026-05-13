package com.example.QuanLyHopDong.infrastructure.Persistence.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "KH_KhachHang")
public class KhachHangjpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MaKhachHang", unique = true)
    private String maKhachHang;

    @Column(name = "TenKhachHang")
    private String tenKhachHang;

    private String email;
    private String soDienThoai;
}
