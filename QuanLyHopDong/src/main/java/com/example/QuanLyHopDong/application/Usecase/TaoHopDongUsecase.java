package com.example.QuanLyHopDong.application.Usecase;

import com.example.QuanLyHopDong.application.Commad.TaoHopDongCommad;
import com.example.QuanLyHopDong.domain.Entity.HopDong;
import com.example.QuanLyHopDong.domain.Repositoryinterface.IHopDongRepository;
import org.springframework.stereotype.Service;

@Service
public class TaoHopDongUsecase {
    private final IHopDongRepository hopDongRepository;
    public TaoHopDongUsecase(IHopDongRepository hopDongRepository){
        this.hopDongRepository = hopDongRepository;

    }
    public Long execute (TaoHopDongCommad commad){
        if (hopDongRepository.existsByMaHopDong(commad.maHopDong())){
            throw new IllegalArgumentException("Da ton tai ma hop dong");

        }

        HopDong hopDong=new HopDong(commad.maHopDong(),commad.khachHangId(),commad.ngayKy(), commad.thoiHan());
        HopDong saveHopDong =hopDongRepository.save(hopDong);
        return saveHopDong.getId();
    }

}
