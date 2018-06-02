package org.harvan.example.pattern.command2.strategy.solver.command;

import java.util.HashMap;
import java.util.Map;

import org.harvan.example.pattern.command2.command.ExecuteTaskSolutionCommand;
import org.harvan.example.pattern.command2.command.ICommand;
import org.harvan.example.pattern.command2.strategy.solution.CreateComplexWebApplicationTaskSolutionStrategy;
import org.harvan.example.pattern.command2.strategy.solution.UnknownTaskSolutionStrategy;
import org.harvan.example.pattern.command2.task.TaskConstant;

public class CoreTaskCommandFactory {
    private static Map<String, ICommand> commandMap;

    private CoreTaskCommandFactory() {
	// hide
    }

    public static ICommand getCommand(String description) {
	ICommand command = commandMap.get(description);

	if (command == null) {
	    command = commandMap.get(null);
	}

	return command;
    }

    static {
	commandMap = new HashMap<>();
	commandMap.put(null, new ExecuteTaskSolutionCommand(new UnknownTaskSolutionStrategy()));
	commandMap.put(TaskConstant.WEB.toString(),
		new ExecuteTaskSolutionCommand(new CreateComplexWebApplicationTaskSolutionStrategy()));
    }
}