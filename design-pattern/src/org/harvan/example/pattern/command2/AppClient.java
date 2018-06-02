package org.harvan.example.pattern.command2;

import org.harvan.example.pattern.command2.invoker.HeadOfITAsInvokerAndClient;
import org.harvan.example.pattern.command2.receiver.division.DivisionConstant;
import org.harvan.example.pattern.command2.task.Task;
import org.harvan.example.pattern.command2.task.TaskConstant;
import org.harvan.example.pattern.command2.task.TaskWarehouse;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public class AppClient {
    private AppClient() {
	// hidden
    }

    public static void main(String[] args) {
	HeadOfITAsInvokerAndClient headOfITAsInvokerAndClient = new HeadOfITAsInvokerAndClient();

	headOfITAsInvokerAndClient.executeJob();
	headOfITAsInvokerAndClient.createWebAppByCoreDivisionCommandInvoker();

	TaskWarehouse.addNewTask(new Task(DivisionConstant.CORE, TaskConstant.WEB.toString()));
	TaskWarehouse.addNewTask(new Task(DivisionConstant.CORE, "itung uang"));
	headOfITAsInvokerAndClient.startUniversalCoreDivisionTaskInvoker();
    }
}