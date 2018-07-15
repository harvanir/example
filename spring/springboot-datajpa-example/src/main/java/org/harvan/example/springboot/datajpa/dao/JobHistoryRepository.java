/*
 * Copyright 2018-2018 the original author or authors.
 */

package org.harvan.example.springboot.datajpa.dao;

import org.harvan.example.springboot.datajpa.model.JobHistory;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (15 Jul 2018)
 *
 */
public interface JobHistoryRepository extends CrudRepository<JobHistory, Long> {
	JobHistory findByJobHistoryId(Long jobHistoryId);
}