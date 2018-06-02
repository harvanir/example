package org.harvan.example.pattern.command2.strategy.solver;

import org.harvan.example.pattern.command2.command.ICommand;
import org.harvan.example.pattern.command2.receiver.division.DivisionConstant;
import org.harvan.example.pattern.command2.strategy.solver.command.CoreTaskCommandFactory;
import org.harvan.example.pattern.command2.task.Task;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class CoreDivisionTaskSolverStrategy implements ITaskSolver {
    @Override
    public void solvingTask(Task task) {
	System.out.print(toString() + task);
	if (task == null) {
	    return;
	}

	showInfo(task);
	solvingTaskInternal(task);
    }

    private void showInfo(Task task) {
	boolean expertise = task.getItDivision() == null || !DivisionConstant.CORE.equals(task.getItDivision());
	if (expertise) {
	    System.out.println("Warning, the division on task is unknown. No guarantee solution.");
	}
    }

    private void solvingTaskInternal(Task task) {
	ICommand command = CoreTaskCommandFactory.getCommand(task.getDescription());
	command.execute();
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + ": ";
    }
}