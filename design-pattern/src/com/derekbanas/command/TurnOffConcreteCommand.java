package com.derekbanas.command;

public class TurnOffConcreteCommand implements Command {
	private ElectronicDeviceReceiver electronicDevice;

	public TurnOffConcreteCommand(ElectronicDeviceReceiver electronicDevice) {
		this.electronicDevice = electronicDevice;
	}

	@Override
	public void execute() {
		electronicDevice.off();
	}
}