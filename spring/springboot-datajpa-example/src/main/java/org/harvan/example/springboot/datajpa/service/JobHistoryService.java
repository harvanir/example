/*
 * Copyright 2018-2018 the original author or authors.
 */

package org.harvan.example.springboot.datajpa.service;

import org.harvan.example.springboot.datajpa.model.JobHistoryDto;
import org.harvan.example.springboot.datajpa.model.JobHistoryRequest;

/**
 * 
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (15 Jul 2018)
 *
 */
public interface JobHistoryService {
	JobHistoryDto findByJobHistoryId(Long jobHistoryId);

	JobHistoryDto create(JobHistoryRequest jobHistoryRequest);

	JobHistoryDto update(JobHistoryRequest jobHistoryRequest);
}