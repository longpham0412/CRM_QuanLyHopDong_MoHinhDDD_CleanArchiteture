package com.example.QuanLyHopDong.infrastructure.Persistence.Repositories;

import com.example.QuanLyHopDong.domain.Entity.HopDong;
import com.example.QuanLyHopDong.domain.Repositoryinterface.IHopDongRepository;
import com.example.QuanLyHopDong.infrastructure.Persistence.Entities.HopDongjpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HopDongRepository implements IHopDongRepository {
    private final HopDongjpaRepository hopDongjpaRepository;
    public HopDongRepository(HopDongjpaRepository hopDongjpaRepository){
        this.hopDongjpaRepository =hopDongjpaRepository;
    }
    @Override
    public boolean existsByMaHopDong(String maHopDong){
        return hopDongjpaRepository.existsByMaHopDong(maHopDong);
    }
    @Override
    public HopDong save (HopDong hopDong){
        HopDongjpaEntity hopDongjpaEntity=new HopDongjpaEntity();
        if (hopDong.getId() != null) {
            hopDongjpaEntity.setId(hopDong.getId());
        }
        hopDongjpaEntity.setMaHopDong(hopDong.getMaHopDong());
        hopDongjpaEntity.setKhachhang_id(hopDong.getKhachHangId());
        hopDongjpaEntity.setTrangThai(hopDong.getTrangThai().name());
        hopDongjpaEntity.setNgayKy(hopDong.getNgayKy());
        hopDongjpaEntity.setThoiHan(hopDong.getThoiHan());
        HopDongjpaEntity savedEntities = hopDongjpaRepository.save(hopDongjpaEntity);
        hopDong.setId(savedEntities.getId());
        return  hopDong;
    }
    @Override
    public List<HopDong> findAll() {
        return hopDongjpaRepository.findAll().stream().map(jpaEntity -> {

            HopDong domain = new HopDong(
                    jpaEntity.getMaHopDong(),
                    jpaEntity.getKhachhang_id(),
                    jpaEntity.getNgayKy(),
                    jpaEntity.getThoiHan()
            );
            domain.setId(jpaEntity.getId());

            return domain;
        }).collect(Collectors.toList());
    }
    @Override
    public Optional<HopDong> findById(Long id) {
        return hopDongjpaRepository.findById(id).map(entity -> {
            HopDong domain = new HopDong(entity.getMaHopDong(), entity.getKhachhang_id(), entity.getNgayKy(), entity.getThoiHan());
            domain.setId(entity.getId());
            return domain;
        });
    }

    @Override
    public void deleteById(Long id) {
        hopDongjpaRepository.deleteById(id);
    }
}
