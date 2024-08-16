package com.example.proyek.controller;

import com.example.proyek.entity.Lokasi;
import com.example.proyek.exception.ResourceNotFoundException;
import com.example.proyek.service.LokasiService;
import com.example.proyek.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lokasi")
public class LokasiController {

    @Autowired
    private LokasiService lokasiService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addLokasi(@RequestBody Lokasi lokasi) {
        Lokasi newLokasi = lokasiService.addLokasi(lokasi);
        return ResponseUtil.createResponse(HttpStatus.CREATED, newLokasi, "Lokasi Sukses Ditambahkan");
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllLokasi() {
        List<Lokasi> lokasiList = lokasiService.getAllLokasi();

        if (lokasiList.isEmpty()) {
            return ResponseUtil.createResponse(HttpStatus.NOT_FOUND, lokasiList, "Lokasi masih kosong");
        }

        return ResponseUtil.createResponse(HttpStatus.OK, lokasiList, "Lokasi list sukses didapatkan");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getLokasiById(@PathVariable Integer id) {
        try {
            Lokasi lokasi = lokasiService.findById(id);
            return ResponseUtil.createResponse(HttpStatus.OK, lokasi, "Lokasi sukses didapatkan");
        }catch (ResourceNotFoundException ex) {
            return ResponseUtil.createNotFoundResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateLokasi(@PathVariable Integer id, @RequestBody Lokasi lokasi) {
        try {
            Lokasi lokasiUpdate = lokasiService.updateLokasi(id, lokasi);
            return ResponseUtil.createResponse(HttpStatus.OK, lokasiUpdate, "Update Lokasi Sukses");
        } catch (ResourceNotFoundException ex) {
            return ResponseUtil.createNotFoundResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteLokasi(@PathVariable Integer id) {
        try {
            lokasiService.deleteLokasi(id);
            return ResponseUtil.createDeleteResponse(HttpStatus.OK, "Delete Lokasi Sukses");
        } catch (ResourceNotFoundException ex) {
            return ResponseUtil.createNotFoundResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
