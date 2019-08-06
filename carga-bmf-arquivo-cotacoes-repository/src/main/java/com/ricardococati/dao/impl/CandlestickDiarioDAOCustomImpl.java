package com.ricardococati.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CandlestickDiarioDAOCustomImpl {

  /*private final MongoTemplate mongoTemplate;

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
  }*/

}
