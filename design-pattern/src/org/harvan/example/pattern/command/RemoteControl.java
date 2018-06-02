package org.harvan.example.pattern.command;

import org.harvan.example.pattern.command.command.ICommand;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public class RemoteControl {
    private ICommand commandOn;
    private ICommand commandOff;
    private String color;

    public RemoteControl(ICommand commandOn, ICommand commandOff) {
	this.commandOn = commandOn;
	this.commandOff = commandOff;
    }

    public String getColor() {
	return color;
    }

    public void setColor(String color) {
	this.color = color;
    }

    public void powerOn() {
	commandOn.execute();
    }

    public void powerOff() {
	commandOff.execute();
    }

    @Override
    public String toString() {
	return "RemoteControl [commandOn=" + commandOn + ", commandOff=" + commandOff + ", color=" + color + "]";
    }
}