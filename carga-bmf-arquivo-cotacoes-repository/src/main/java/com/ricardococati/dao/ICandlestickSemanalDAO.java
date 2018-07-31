package com.ricardococati.dao;

import com.ricardococati.dto.CandlestickSemanal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICandlestickSemanalDAO extends MongoRepository<CandlestickSemanal, String> {

}
