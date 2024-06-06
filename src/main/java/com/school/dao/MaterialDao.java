package com.school.dao;

import com.school.model.Material;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialDao extends MongoRepository<Material, String> { // Usando MongoRepository
}
