package org.harvan.example.pattern.command2.task;

import java.util.ArrayList;
import java.util.List;

import org.harvan.example.pattern.command2.receiver.division.DivisionConstant;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class TaskWarehouse {
    private static List<Task> masterTasks = new ArrayList<>();

    private TaskWarehouse() {
	// hidden
    }

    public static void addNewTask(Task task) {
	masterTasks.add(task);
    }

    public static List<Task> getTask(DivisionConstant division) {
	List<Task> newTasks = new ArrayList<>();
	List<Task> buffer = new ArrayList<>(masterTasks);

	for (Task buff : buffer) {
	    if (division.equals(buff.getItDivision())) {
		newTasks.add(buff);
	    }
	}

	if (!newTasks.isEmpty()) {
	    masterTasks.removeAll(newTasks);
	}

	return newTasks;
    }
}