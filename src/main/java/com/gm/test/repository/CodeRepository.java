package com.gm.test.repository;

import com.gm.test.model.Code;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CodeRepository extends MongoRepository<Code, String> {

    public List<Code> findByCodeListCode(String codeListCode);

//    @Query(value = "{'code' : ?0}")
    public Code findByCode(String code);

    public void deleteByCode(String code);
}
