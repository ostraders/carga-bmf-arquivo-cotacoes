package com.ricardococati.dao;

import com.ricardococati.dto.Header;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IHeaderDAO  extends MongoRepository<Header, String> {

}
