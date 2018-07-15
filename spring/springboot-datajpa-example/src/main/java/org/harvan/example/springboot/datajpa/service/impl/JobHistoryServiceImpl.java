/*
 * Copyright 2018-2018 the original author or authors.
 */

package org.harvan.example.springboot.datajpa.service.impl;

import java.util.Date;

import org.harvan.example.springboot.datajpa.dao.JobHistoryRepository;
import org.harvan.example.springboot.datajpa.model.JobHistory;
import org.harvan.example.springboot.datajpa.model.JobHistoryDto;
import org.harvan.example.springboot.datajpa.model.JobHistoryRequest;
import org.harvan.example.springboot.datajpa.service.BeanMapper;
import org.harvan.example.springboot.datajpa.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (15 Jul 2018)
 *
 */
@Service
public class JobHistoryServiceImpl implements JobHistoryService {
	private BeanMapper beanMapper = BeanMapper.INSTANCE;
	private JobHistoryRepository jobHistoryRepository;

	@Autowired
	public void setJobHistoryRepository(JobHistoryRepository jobHistoryRepository) {
		this.jobHistoryRepository = jobHistoryRepository;
	}

	@Override
	public JobHistoryDto findByJobHistoryId(Long jobHistoryId) {
		return beanMapper.map(jobHistoryRepository.findByJobHistoryId(jobHistoryId));
	}

	@Override
	public JobHistoryDto create(JobHistoryRequest jobHistoryRequest) {
		jobHistoryRequest.setStartTime(new Date());
		jobHistoryRequest.setInstanceName(Thread.currentThread().getName());

		return beanMapper.map(jobHistoryRepository.save(beanMapper.map(jobHistoryRequest)));
	}

	@Override
	public JobHistoryDto update(JobHistoryRequest jobHistoryRequest) {
		JobHistory persist = jobHistoryRepository.findByJobHistoryId(jobHistoryRequest.getJobHistoryId());

		if (persist != null) {
			persist.setMessage(jobHistoryRequest.getMessage());

			return beanMapper.map(jobHistoryRepository.save(persist));
		}

		throw new RuntimeException("Data not found"); // NOSONAR
	}
}