package com.example.proyek.service;

import com.example.proyek.entity.Lokasi;
import com.example.proyek.exception.ResourceNotFoundException;
import com.example.proyek.repository.LokasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LokasiService {

    @Autowired
    private LokasiRepository lokasiRepository;

    public Lokasi addLokasi(Lokasi lokasi) {
        return lokasiRepository.save(lokasi);
    }

    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    public Lokasi updateLokasi(Integer id, Lokasi lokasi) {

        Lokasi existingLokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lokasi tidak ditemukan dengan id " + id));
        existingLokasi.setNamaLokasi(lokasi.getNamaLokasi());
        existingLokasi.setNegara(lokasi.getNegara());
        existingLokasi.setProvinsi(lokasi.getProvinsi());
        existingLokasi.setKota(lokasi.getKota());
        return lokasiRepository.save(existingLokasi);
    }

    public Lokasi findById(Integer id) {
        return lokasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lokasi tidak ditemukan dengan id " + id));
    }

    public void deleteLokasi(Integer id) {
        if (!lokasiRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lokasi tidak ditemukan dengan id " + id);
        }
        lokasiRepository.deleteById(id);
    }
}

