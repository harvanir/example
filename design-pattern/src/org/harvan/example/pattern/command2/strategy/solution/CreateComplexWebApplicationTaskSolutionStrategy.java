package org.harvan.example.pattern.command2.strategy.solution;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class CreateComplexWebApplicationTaskSolutionStrategy implements ITaskSolution {
    @Override
    public void executeSolution() {
	System.out.print(toString());
	System.out.println("action: Create complex web application.");
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + ": ";
    }
}