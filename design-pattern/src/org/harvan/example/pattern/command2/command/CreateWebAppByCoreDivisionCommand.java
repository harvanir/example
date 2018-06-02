package org.harvan.example.pattern.command2.command;

import org.harvan.example.pattern.command2.receiver.division.DivisionConstant;
import org.harvan.example.pattern.command2.receiver.division.IDivision;
import org.harvan.example.pattern.command2.task.Task;
import org.harvan.example.pattern.command2.task.TaskConstant;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class CreateWebAppByCoreDivisionCommand extends AbstractDivisionCommand {
    public CreateWebAppByCoreDivisionCommand(IDivision division) {
	super(division);
    }

    @Override
    public void execute() {
	System.out.print(toString());
	Task task = new Task(DivisionConstant.CORE, TaskConstant.WEB.toString());
	getDivision().retreiveTask(task);
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + ": ";
    }
}