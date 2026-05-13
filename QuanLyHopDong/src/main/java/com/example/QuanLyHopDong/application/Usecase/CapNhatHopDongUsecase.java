package com.example.QuanLyHopDong.application.Usecase;

import com.example.QuanLyHopDong.application.Commad.CapNhatHopDongCommad;
import com.example.QuanLyHopDong.domain.Entity.HopDong;
import com.example.QuanLyHopDong.domain.Repositoryinterface.IHopDongRepository;
import org.springframework.stereotype.Service;

@Service
public class CapNhatHopDongUsecase {
    private final IHopDongRepository repository;

    public CapNhatHopDongUsecase(IHopDongRepository repository) { this.repository = repository; }

    public void execute(CapNhatHopDongCommad commad) {
        HopDong hopDong = repository.findById(commad.id())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy hợp đồng"));

        hopDong.capNhatThongTin(commad.maHopDong(), commad.khachHangId(), commad.ngayKy(), commad.thoiHan());
        repository.save(hopDong);
    }
}
