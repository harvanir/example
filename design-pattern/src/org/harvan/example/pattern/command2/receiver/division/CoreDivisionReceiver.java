package org.harvan.example.pattern.command2.receiver.division;

import org.harvan.example.pattern.command2.strategy.solver.CoreDivisionTaskSolverStrategy;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class CoreDivisionReceiver extends DivisionReceiver {
    public CoreDivisionReceiver() {
	taskSolver = new CoreDivisionTaskSolverStrategy();
    }
}