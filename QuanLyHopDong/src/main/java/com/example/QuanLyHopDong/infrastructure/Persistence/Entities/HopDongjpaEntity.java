package com.example.QuanLyHopDong.infrastructure.Persistence.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HD_HopDong")
public class HopDongjpaEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "MaHopDong", unique = true, nullable = false)
    private String maHopDong;
    @Column(name = "KhachHang_Id", nullable = false)
    private Long khachhang_id;
    @Column(name = "NgayKy")
    private LocalDate ngayKy;
    @Column(name = "ThoiHan")
    private Integer thoiHan;
    @Column(name = "TrangThai")
    private String trangThai;


}
