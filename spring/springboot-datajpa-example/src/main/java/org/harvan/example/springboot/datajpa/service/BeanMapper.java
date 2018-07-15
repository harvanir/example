/*
 * Copyright 2018-2018 the original author or authors.
 */

package org.harvan.example.springboot.datajpa.service;

import org.harvan.example.springboot.datajpa.model.JobHistory;
import org.harvan.example.springboot.datajpa.model.JobHistoryDto;
import org.harvan.example.springboot.datajpa.model.JobHistoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (15 Jul 2018)
 *
 */
@Mapper
public interface BeanMapper {
	BeanMapper INSTANCE = Mappers.getMapper(BeanMapper.class);

	JobHistoryDto map(JobHistory jobHistory);

	JobHistory map(JobHistoryRequest jobHistoryRequest);
}