package org.harvan.example.pattern.command2.receiver.division;

import org.harvan.example.pattern.command2.strategy.solver.ITaskSolver;
import org.harvan.example.pattern.command2.task.Task;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class DivisionReceiver implements IDivision {
    protected ITaskSolver taskSolver;

    @Override
    public void retreiveTask(Task task) {
	System.out.print(toString());
	taskSolver.solvingTask(task);
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + ": ";
    }
}