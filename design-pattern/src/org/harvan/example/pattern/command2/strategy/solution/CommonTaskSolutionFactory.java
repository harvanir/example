package org.harvan.example.pattern.command2.strategy.solution;

import java.util.HashMap;
import java.util.Map;

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
public class CommonTaskSolutionFactory {
    private static Map<String, ITaskSolution> mappedTaskSolution;

    private CommonTaskSolutionFactory() {
	// hidden
    }

    public static ITaskSolution getTaskSolution(Task task) {
	return mappedTaskSolution.get(task.getDescription());
    }

    static {
	mappedTaskSolution = new HashMap<>();

	mappedTaskSolution.put(TaskConstant.WEB.toString(), new CreateCommonWebApplicationTaskSolutionStrategy());
    }
}