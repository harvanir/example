package org.harvan.example.pattern.command2.command;

import org.harvan.example.pattern.command2.strategy.solution.ITaskSolution;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class ExecuteTaskSolutionCommand implements ICommand {
    private ITaskSolution solution;

    public ExecuteTaskSolutionCommand(ITaskSolution solution) {
	this.solution = solution;
    }

    @Override
    public void execute() {
	System.out.print(toString());
	solution.executeSolution();
    }

    @Override
    public String toString() {
	return " : " + getClass().getSimpleName() + ": ";
    }
}