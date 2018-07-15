/*
 * Copyright 2018-2018 the original author or authors.
 */

package org.harvan.example.springboot.datajpa.model;

import java.util.Date;

/**
 * 
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (15 Jul 2018)
 *
 */
public class JobHistoryDto {
	public Long jobHistoryId;
	public Date startTime;
	public Date endTime;
	public String jobName;
	public String instanceName;
	public String status;
	public String message;

	public Long getJobHistoryId() {
		return jobHistoryId;
	}

	public void setJobHistoryId(Long jobHistoryId) {
		this.jobHistoryId = jobHistoryId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobHistoryDto [jobHistoryId=");
		builder.append(jobHistoryId);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", jobName=");
		builder.append(jobName);
		builder.append(", instanceName=");
		builder.append(instanceName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
}