package com.ricardococati.dao;

import com.ricardococati.dto.ArquivoDTO;
import com.ricardococati.dto.Cotacao;
import com.ricardococati.dto.DetalheSegmentoGDTO;
import com.ricardococati.dto.Header;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IBMFCargaDAO  extends MongoRepository<Cotacao, String> {

}
