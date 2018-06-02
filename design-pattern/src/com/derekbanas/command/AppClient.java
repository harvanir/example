package com.derekbanas.command;

public class AppClient {
	public static void main(String[] args) {
		ElectronicDeviceReceiver tv = ElectronicDeviceFactory.getDevice("tv");
		Command onCommand = new TurnOnConcreteCommand(tv);
		DeviceButtonInvoker onButtonInvoker = new DeviceButtonInvoker(onCommand);

		onButtonInvoker.press();
	}
}