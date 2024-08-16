package com.example.proyek.controller;

import com.example.proyek.entity.Lokasi;
import com.example.proyek.entity.Proyek;
import com.example.proyek.exception.ResourceNotFoundException;
import com.example.proyek.service.ProyekService;
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
@RequestMapping("/proyek")
public class ProyekController {

    @Autowired
    private ProyekService proyekService;

    @PostMapping
    public ResponseEntity<Map<String,Object>> addProyek(@RequestBody Proyek proyek) {
        Proyek newProyek = proyekService.addProyek(proyek);
        return ResponseUtil.createResponse(HttpStatus.CREATED, newProyek, "Proyek Sukses Ditambahkan");
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProyek() {
        List<Proyek> proyekList = proyekService.getAllProyek();
        if (proyekList.isEmpty()) {
            return ResponseUtil.createResponse(HttpStatus.NOT_FOUND, proyekList, "Proyek masih kosong");
        }
        return ResponseUtil.createResponse(HttpStatus.OK, proyekList, "Proyek list sukses didapatkan");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getProyekById(@PathVariable Integer id) {
        try {
            Proyek proyek = proyekService.findById(id);
            return ResponseUtil.createResponse(HttpStatus.OK,proyek,"Proyek sukses didapatkan");
        }catch(ResourceNotFoundException ex) {
            return ResponseUtil.createNotFoundResponse(HttpStatus.NOT_FOUND,ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateProyek(@PathVariable Integer id, @RequestBody Proyek proyek) {
        try {
            Proyek updateProyek = proyekService.updateProyek(id, proyek);
            return ResponseUtil.createResponse(HttpStatus.OK, updateProyek, "Update Proyek Sukses");
        } catch (ResourceNotFoundException ex) {
            return ResponseUtil.createNotFoundResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteProyek(@PathVariable Integer id) {
        try {
            proyekService.deleteProyek(id);
            return ResponseUtil.createDeleteResponse(HttpStatus.OK, "Delete Proyek Sukses");
        } catch (ResourceNotFoundException ex) {
            return ResponseUtil.createNotFoundResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}

