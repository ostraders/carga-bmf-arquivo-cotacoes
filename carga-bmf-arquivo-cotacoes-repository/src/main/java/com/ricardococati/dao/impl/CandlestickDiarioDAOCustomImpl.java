package com.ricardococati.dao.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import com.ricardococati.dao.ICandlestickDiarioDAOCustom;
import com.ricardococati.dto.Cotacao;
import com.ricardococati.dto.Empresa;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CandlestickDiarioDAOCustomImpl implements ICandlestickDiarioDAOCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public List<Empresa> findAllCodneg() {
    Aggregation agg = newAggregation(
        match(Criteria.where("codbdi").in("02", "05", "06", "07", "08", "09", "10")
            .and("tpmerc").is(10)
        ),
        group("codneg"),
        sort(Direction.ASC, "_id")
    );

    AggregationResults<Empresa> groupResults
        = mongoTemplate.aggregate(agg, Cotacao.class, Empresa.class);
    List<Empresa> result = groupResults.getMappedResults();

    return result;
  }

}
