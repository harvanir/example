package org.harvan.example.pattern.command2.invoker;

import org.harvan.example.pattern.command2.command.CommandConstant;
import org.harvan.example.pattern.command2.command.CoreDivisionCommandFactory;
import org.harvan.example.pattern.command2.command.ICommand;
import org.harvan.example.pattern.command2.command.UniversalCoreDivisionCommand;
import org.harvan.example.pattern.command2.receiver.division.DivisionConstant;
import org.harvan.example.pattern.command2.receiver.division.DivisionFactory;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class HeadOfITAsInvokerAndClient {
    private ICommand universalCoreDivisionCommand;
    private ICommand createWebAppCoreDivisionCommand;

    public HeadOfITAsInvokerAndClient() {
	prepareCoreDivisionCommandClient();
	prepareUniversalCoreDivisionCommandClient();
    }

    public void executeJob() {
	createWebAppCoreDivisionCommand.execute();
    }

    public void startUniversalCoreDivisionTaskInvoker() {
	universalCoreDivisionCommand.execute();
    }

    public void createWebAppByCoreDivisionCommandInvoker() {
	createWebAppCoreDivisionCommand.execute();
    }

    private void prepareCoreDivisionCommandClient() {
	createWebAppCoreDivisionCommand = CoreDivisionCommandFactory.getCommand(CommandConstant.CREATE_WEB);
    }

    private void prepareUniversalCoreDivisionCommandClient() {
	universalCoreDivisionCommand = new UniversalCoreDivisionCommand(
		DivisionFactory.getSuitableDivision(DivisionConstant.CORE));
    }
}