package com.derekbanas.command;

public class TelevisionReceiver implements ElectronicDeviceReceiver {
	@Override
	public void on() {
		System.out.println("TV is ON.");
	}

	@Override
	public void off() {
		System.out.println("TV is OFF.");
	}
}