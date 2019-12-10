package com.ricardococati.service;

import com.ricardococati.model.dto.SplitInplit;

public interface ISplitInplitService {

  Boolean split(final SplitInplit splitInplit);

  Boolean inplit(SplitInplit splitInplit);
}
