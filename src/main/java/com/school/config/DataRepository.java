package com.school.config;

import javax.xml.crypto.Data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends MongoRepository<Data, String> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}
