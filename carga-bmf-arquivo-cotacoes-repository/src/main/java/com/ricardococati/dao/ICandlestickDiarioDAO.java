package com.ricardococati.dao;

import com.ricardococati.dto.CandlestickDiario;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICandlestickDiarioDAO extends MongoRepository<CandlestickDiario, String> {

  List<CandlestickDiario> findByNomres(String nomres);

}
