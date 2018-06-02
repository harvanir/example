package org.harvan.example.pattern.command2.task;

import org.harvan.example.pattern.command2.receiver.division.DivisionConstant;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class Task {
    private DivisionConstant itDivision;
    private String description;

    public Task(DivisionConstant itDivision, String description) {
	this.itDivision = itDivision;
	this.description = description;
    }

    public DivisionConstant getItDivision() {
	return itDivision;
    }

    public void setItDivision(DivisionConstant itDivision) {
	this.itDivision = itDivision;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return "Task [itDivision=" + itDivision + ", description=" + description + "]";
    }
}