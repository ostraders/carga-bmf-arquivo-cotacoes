package com.ricardococati.carga.usecases.batchprocess.tokenize;

import static java.util.Objects.isNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.batch.item.file.transform.AbstractLineTokenizer;
import org.springframework.batch.item.file.transform.Range;

public class FixedLengthTokenizer extends AbstractLineTokenizer implements Serializable {

  private static final long serialVersionUID = -96646969236134206L;
  private static final String FORMATA_245_POSICOES = "%-245s";
  private Range[] ranges;
  private int maxRange = 0;
  boolean open = false;

  public void setColumns(Range[] ranges) {
    this.ranges = Arrays.asList(ranges).toArray(new Range[ranges.length]);
    calculateMaxRange(ranges);
  }

  private void calculateMaxRange(Range[] ranges) {
    if (isNull(ranges) || ranges.length == 0) {
      maxRange = 0;
      return;
    }
    open = false;
    maxRange = ranges[0].getMin();

    for (Range range : ranges) {
      int upperBound;
      if (range.hasMaxValue()) {
        upperBound = range.getMax();
      } else {
        upperBound = range.getMin();
        if (upperBound > maxRange) {
          open = true;
        }
      }
      if (upperBound > maxRange) {
        maxRange = upperBound;
      }
    }

  }

  @Override
  protected List<String> doTokenize(String line) {
    final String linhaTratada = getTratamentoLinhaTamanhoFixo(line);
    List<String> tokens = new ArrayList<String>(ranges.length);
    int lineLength;
    String token;

    lineLength = linhaTratada.length();

    for (Range range : ranges) {
      int startPos = range.getMin() - 1;
      int endPos = range.getMax();
      if (lineLength >= endPos) {
        token = linhaTratada.substring(startPos, endPos);
      } else if (lineLength >= startPos) {
        token = linhaTratada.substring(startPos);
      } else {
        token = "";
      }
      tokens.add(token);
    }
    return tokens;
  }

  private String getTratamentoLinhaTamanhoFixo(String line) {
    return String.format(FORMATA_245_POSICOES, line);
  }

}
