package com.example.QuanLyHopDong.application.Usecase;

import com.example.QuanLyHopDong.domain.Entity.HopDong;
import com.example.QuanLyHopDong.domain.Repositoryinterface.IHopDongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayHopDong {
    private final IHopDongRepository hopDongRepository;

    public LayHopDong(IHopDongRepository hopDongRepository) {
        this.hopDongRepository = hopDongRepository;
    }

    public List<HopDong> execute() {
        return hopDongRepository.findAll();
    }
}
