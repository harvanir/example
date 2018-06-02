package org.harvan.example.pattern.command;

import org.harvan.example.pattern.command.builder.SmartHomeLightingVer1Builder;
import org.harvan.example.pattern.command.builder.SmartHomeRemoteControlVer1Builder;
import org.harvan.example.pattern.command.container.Container;
import org.harvan.example.pattern.command.container.ElectroMagnetic;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public class App {
    public static void main(String[] args) {
	Container electroMagnetic = new ElectroMagnetic("OpenSpace ElectroMagnetic container");
	RemoteControl remote = new SmartHomeRemoteControlVer1Builder(electroMagnetic).build();
	new SmartHomeLightingVer1Builder(electroMagnetic).build();
	new SmartHomeLightingVer1Builder(electroMagnetic).build();

	remote.powerOn();
	remote.powerOff();
	remote.powerOn();
    }
}