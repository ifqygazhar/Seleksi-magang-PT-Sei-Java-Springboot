package com.example.proyek.service;

import com.example.proyek.entity.Lokasi;
import com.example.proyek.entity.Proyek;
import com.example.proyek.exception.ResourceNotFoundException;
import com.example.proyek.repository.LokasiRepository;
import com.example.proyek.repository.ProyekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProyekService {

    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private LokasiRepository lokasiRepository;

    public Proyek addProyek(Proyek proyek) {
        return proyekRepository.save(proyek);
    }

    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    public Proyek updateProyek(Integer id, Proyek proyek) {
        Proyek existingProyek = proyekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyek tidak ditemukan"));

        existingProyek.setNamaProyek(proyek.getNamaProyek());
        existingProyek.setClient(proyek.getClient());
        existingProyek.setTglMulai(proyek.getTglMulai());
        existingProyek.setTglSelesai(proyek.getTglSelesai());
        existingProyek.setPimpinanProyek(proyek.getPimpinanProyek());
        existingProyek.setKeterangan(proyek.getKeterangan());


        existingProyek.getLokasi().clear();


        for (Lokasi lokasi : proyek.getLokasi()) {
            Lokasi lokasiToAdd = lokasiRepository.findById(lokasi.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lokasi tidak ditemukan"));
            existingProyek.getLokasi().add(lokasiToAdd);
        }

        return proyekRepository.save(existingProyek);
    }

    public Proyek findById(Integer id) {
        return  proyekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyek tidak ditemukan"));
    }

    public void deleteProyek(Integer id) {
        if (!proyekRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lokasi tidak ditemukan dengan id " + id);
        }
        proyekRepository.deleteById(id);
    }
}

