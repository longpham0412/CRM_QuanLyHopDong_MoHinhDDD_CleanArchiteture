package com.example.QuanLyHopDong.infrastructure.Persistence.Controller;

import com.example.QuanLyHopDong.application.Commad.TaoKhachHangCommad;
import com.example.QuanLyHopDong.application.Usecase.LayDanhSachKhachHangUseCase;
import com.example.QuanLyHopDong.application.Usecase.TaoKhachHangUseCase;
import com.example.QuanLyHopDong.domain.Entity.KhachHang;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/khachhang")
@CrossOrigin("*")
public class KhachHangController {
    private final TaoKhachHangUseCase taoKhachHangUsecase;
    private final LayDanhSachKhachHangUseCase layDanhSachKhachHangUsecase;

    public KhachHangController(TaoKhachHangUseCase  taoKhachHangUsecase, LayDanhSachKhachHangUseCase layDanhSachKhachHangUsecase) {
        this.taoKhachHangUsecase = taoKhachHangUsecase;
        this.layDanhSachKhachHangUsecase = layDanhSachKhachHangUsecase;
    }

    @PostMapping
    public ResponseEntity<Long> taoKhachHang(@RequestBody TaoKhachHangCommad commad) {
        return ResponseEntity.ok(taoKhachHangUsecase.execute(commad));
    }

    @GetMapping
    public ResponseEntity<List<KhachHang>> layTatCa() {
        return ResponseEntity.ok(layDanhSachKhachHangUsecase.execute());
    }
}
