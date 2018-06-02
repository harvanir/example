package org.harvan.example.pattern.command2.command;

import java.util.EnumMap;
import java.util.Map;

import org.harvan.example.pattern.command2.receiver.division.DivisionConstant;
import org.harvan.example.pattern.command2.receiver.division.DivisionFactory;
import org.harvan.example.pattern.command2.receiver.division.IDivision;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class CoreDivisionCommandFactory {
    private static Map<CommandConstant, ICommand> commandMap;
    private static final IDivision DIVISION = DivisionFactory.getSuitableDivision(DivisionConstant.CORE);

    private CoreDivisionCommandFactory() {
	// hidden
    }

    public static ICommand getCommand(CommandConstant constant) {
	return commandMap.get(constant);
    }

    static {
	commandMap = new EnumMap<>(CommandConstant.class);

	commandMap.put(CommandConstant.CREATE_WEB, new CreateWebAppByCoreDivisionCommand(DIVISION));
    }
}