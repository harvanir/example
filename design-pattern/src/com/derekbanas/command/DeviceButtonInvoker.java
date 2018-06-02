package com.derekbanas.command;

public class DeviceButtonInvoker {
	private Command command;

	public DeviceButtonInvoker(Command command) {
		this.command = command;
	}

	public void press() {
		command.execute();
	}
}