package com.ricardococati.service;

import com.ricardococati.model.dto.SplitInplit;

public interface SplitInplitService {

  Boolean split(final SplitInplit splitInplit);

  Boolean inplit(SplitInplit splitInplit);
}
