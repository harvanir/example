package org.harvan.example.pattern.command2.command;

import java.util.List;

import org.harvan.example.pattern.command2.receiver.division.DivisionConstant;
import org.harvan.example.pattern.command2.receiver.division.IDivision;
import org.harvan.example.pattern.command2.task.Task;
import org.harvan.example.pattern.command2.task.TaskWarehouse;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class UniversalCoreDivisionCommand implements ICommand {
    private IDivision division;

    public UniversalCoreDivisionCommand(IDivision division) {
	this.division = division;
    }

    @Override
    public void execute() {
	List<Task> tasks = TaskWarehouse.getTask(DivisionConstant.CORE);

	if (!tasks.isEmpty()) {
	    for (Task task : tasks) {
		System.out.print(toString());
		division.retreiveTask(task);
	    }
	} else {
	    System.out.println("No task found");
	}
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + ": ";
    }
}