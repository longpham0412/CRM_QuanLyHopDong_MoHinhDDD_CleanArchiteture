package com.example.QuanLyHopDong.infrastructure.Persistence.Controller;

import com.example.QuanLyHopDong.application.Commad.CapNhatHopDongCommad;
import com.example.QuanLyHopDong.application.Commad.TaoHopDongCommad;
import com.example.QuanLyHopDong.application.Usecase.CapNhatHopDongUsecase;
import com.example.QuanLyHopDong.application.Usecase.LayHopDong;
import com.example.QuanLyHopDong.application.Usecase.TaoHopDongUsecase;
import com.example.QuanLyHopDong.application.Usecase.XoaHopDongUsecase;
import com.example.QuanLyHopDong.domain.Entity.HopDong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hopdong")
@CrossOrigin("*")
public class HopDongController {
    private final TaoHopDongUsecase taoHopDongUseCase;
    private final LayHopDong layTatCaHopDongUsecase;
  private final CapNhatHopDongUsecase capNhatHopDongUsecase;
  private final XoaHopDongUsecase xoaHopDongUsecase;
    public HopDongController(TaoHopDongUsecase taoHopDongUseCase,LayHopDong layTatCaHopDongUsecase, CapNhatHopDongUsecase capNhatHopDongUsecase, XoaHopDongUsecase xoaHopDongUsecase) {
        this.taoHopDongUseCase = taoHopDongUseCase;
        this.layTatCaHopDongUsecase = layTatCaHopDongUsecase;
        this.capNhatHopDongUsecase= capNhatHopDongUsecase;
        this.xoaHopDongUsecase=xoaHopDongUsecase;
    }

    @PostMapping
    public ResponseEntity<String> taoMoiHopDong(@RequestBody TaoHopDongCommad request) {
        try {
            Long id = taoHopDongUseCase.execute(request);
            return ResponseEntity.ok("Tạo hợp đồng thành công với ID: " + id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<HopDong>> layDanhSachHopDong() {
        List<HopDong> danhSach = layTatCaHopDongUsecase.execute();
        return ResponseEntity.ok(danhSach);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> capNhat(@PathVariable Long id, @RequestBody CapNhatHopDongCommad request) {
        try {

            capNhatHopDongUsecase.execute(new CapNhatHopDongCommad(id, request.maHopDong(), request.khachHangId(), request.ngayKy(), request.thoiHan()));
            return ResponseEntity.ok("Cập nhật thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoa(@PathVariable Long id) {
        try {
            xoaHopDongUsecase.execute(id);
            return ResponseEntity.ok("Xóa thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
