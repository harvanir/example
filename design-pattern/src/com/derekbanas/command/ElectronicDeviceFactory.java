package com.derekbanas.command;

import java.util.HashMap;
import java.util.Map;

public class ElectronicDeviceFactory {
	private static Map<String, ElectronicDeviceReceiver> deviceMap;

	private ElectronicDeviceFactory() {
		// hidden
	}

	public static ElectronicDeviceReceiver getDevice(String deviceName) {
		return deviceMap.get(deviceName);
	}

	static {
		deviceMap = new HashMap<>();

		deviceMap.put("tv", new TelevisionReceiver());
	}
}
