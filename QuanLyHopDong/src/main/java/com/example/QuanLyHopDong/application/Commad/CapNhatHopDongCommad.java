package com.example.QuanLyHopDong.application.Commad;

import java.time.LocalDate;

public record CapNhatHopDongCommad(Long id, String maHopDong, Long khachHangId, LocalDate ngayKy, Integer thoiHan) {
}
