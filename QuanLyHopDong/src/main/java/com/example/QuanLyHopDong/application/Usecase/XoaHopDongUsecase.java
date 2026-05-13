package com.example.QuanLyHopDong.application.Usecase;

import com.example.QuanLyHopDong.domain.Repositoryinterface.IHopDongRepository;
import org.springframework.stereotype.Service;

@Service
public class XoaHopDongUsecase {
    private final IHopDongRepository repository;

    public XoaHopDongUsecase(IHopDongRepository repository) { this.repository = repository; }

    public void execute(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Không tìm thấy hợp đồng để xóa");
        }
        repository.deleteById(id);
    }

}
