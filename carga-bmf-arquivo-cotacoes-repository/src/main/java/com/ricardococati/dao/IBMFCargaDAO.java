package com.ricardococati.dao;

import com.ricardococati.dto.Cotacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBMFCargaDAO  extends MongoRepository<Cotacao, String> {

}
