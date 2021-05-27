package com.ricardococati.carga.config;

import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class CustomPageImpl<T> extends PageImpl<T> {

   public CustomPageImpl(final List<T> content, final Pageable pageable, final long total) {
    super(content, pageable, total);
  }

  public CustomPageImpl(final List<T> content) {
    super(content);
  }

  public CustomPageImpl() {
    super(Collections.emptyList());
  }
}
