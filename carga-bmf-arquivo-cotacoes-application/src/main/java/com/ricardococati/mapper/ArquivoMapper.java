package com.ricardococati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ricardococati.dto.ArquivoDTO;

public class ArquivoMapper implements RowMapper<ArquivoDTO> {

	@Override
	public ArquivoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ArquivoDTO empresaOperadora = new ArquivoDTO();
		empresaOperadora.setCodEmpFornax(rs.getLong("codEmpFornax"));
		return empresaOperadora;
	}

}
