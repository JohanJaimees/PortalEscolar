package com.school.config;

import javax.xml.crypto.Data;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataRepository dataRepository;

    public DataController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @PostMapping
    public ResponseEntity<Data> insertData(@RequestBody Data newData) {
        Data savedData = dataRepository.save(newData);
        return ResponseEntity.ok(savedData);
    }
}
