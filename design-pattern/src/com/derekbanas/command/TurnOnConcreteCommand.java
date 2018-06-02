package com.derekbanas.command;

public class TurnOnConcreteCommand implements Command {
	private ElectronicDeviceReceiver electronicDevice;

	public TurnOnConcreteCommand(ElectronicDeviceReceiver electronicDevice) {
		this.electronicDevice = electronicDevice;
	}

	@Override
	public void execute() {
		electronicDevice.on();
	}
}