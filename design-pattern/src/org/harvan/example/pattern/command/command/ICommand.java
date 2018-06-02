package org.harvan.example.pattern.command.command;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public interface ICommand {
    public void execute();

    public void rollback();
}