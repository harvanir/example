package org.harvan.example.pattern.command.builder;

import java.util.HashMap;
import java.util.Map;

import org.harvan.example.pattern.command.appliance.SmartHomeAppliance;
import org.harvan.example.pattern.command.command.ICommand;
import org.harvan.example.pattern.command.container.Container;
import org.harvan.example.pattern.command.container.ElectroMagnetic;
import org.harvan.example.pattern.command.itemclient.Item;
import org.harvan.example.pattern.command.itemclient.Lamp;
import org.harvan.example.pattern.command.media.ElectricalVoltage;
import org.harvan.example.pattern.command.media.ElectroMagneticSignal;
import org.harvan.example.pattern.command.receiver.DigitalToDigitalReceiverStrategy;
import org.harvan.example.pattern.command.receiver.InfraRedToDigitalReceiverStrategy;
import org.harvan.example.pattern.command.receiver.Receiver;
import org.harvan.example.pattern.command.transmitter.Transmitter;
import org.harvan.example.pattern.command.transmitter.UniversalTransmitterStrategy;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public class SmartHomeLightingVer1Builder extends SmartHomeApplianceBuilder {
    public SmartHomeLightingVer1Builder(Container container) {
	super(container);
    }

    @Override
    public SmartHomeAppliance build() {
	// create electrical container inside appliance
	// create transmitter from infra red module to controller appliance
	// create infra red receiver module with its transmitter attached
	Container infraRedControllerContainer = new ElectroMagnetic("Internal electrical ElectroMagnetic container");
	Transmitter infraRedToControllerTransmitter = new UniversalTransmitterStrategy(
		"InfraRed to controller transmitter", infraRedControllerContainer);
	Receiver infraRedReceiverModule = new InfraRedToDigitalReceiverStrategy("InfraRed receiver module",
		infraRedToControllerTransmitter);

	// create controller
	Receiver controllerReceiver = createController();

	// attach container and receiver module to appliance
	SmartHomeAppliance appliance = new SmartHomeAppliance(container, infraRedReceiverModule,
		infraRedControllerContainer, controllerReceiver);

	appliance.setBrand("Scheinder");
	appliance.setColor("White");
	appliance.setModel("SC1122");

	return appliance;
    }

    private Item prepareItem() {
	Lamp lamp = new Lamp();
	lamp.setBrand("Phillips");
	lamp.setModel("FP1234");

	return lamp;
    }

    private Receiver createController() {
	// prepare item to control by receiver module
	Item item = prepareItem();

	// create command mapping when controller as a transmitter do transmit()
	// create controller as a transmitter
	Map<ElectroMagneticSignal, ICommand> commandMap = createCommandMap(item);
	Transmitter controller = new Controller(commandMap);

	return new DigitalToDigitalReceiverStrategy("Controller as receiver", controller);
    }

    private Map<ElectroMagneticSignal, ICommand> createCommandMap(Item item) {
	ICommand commandOn = new CommandOn(item);
	ICommand commandOff = new CommandOff(item);

	Map<ElectroMagneticSignal, ICommand> commandMap = new HashMap<>();
	ElectroMagneticSignal signal = new ElectricalVoltage();

	signal.setData("on");
	commandMap.put(signal, commandOn);

	signal = new ElectricalVoltage();
	signal.setData("off");
	commandMap.put(signal, commandOff);

	return commandMap;
    }

    private class CommandOn implements ICommand {
	private Item item;

	public CommandOn(Item item) {
	    this.item = item;
	}

	@Override
	public void execute() {
	    item.on();
	}

	@Override
	public void rollback() {
	    item.off();
	}

    }

    private class CommandOff implements ICommand {
	private Item item;

	public CommandOff(Item item) {
	    this.item = item;
	}

	@Override
	public void execute() {
	    item.off();
	}

	@Override
	public void rollback() {
	    item.on();
	}
    }

    private class Controller implements Transmitter {
	private Map<ElectroMagneticSignal, ICommand> commandMap;

	public Controller(Map<ElectroMagneticSignal, ICommand> commandMap) {
	    this.commandMap = commandMap;
	}

	@Override
	public void transmit(ElectroMagneticSignal electroMagneticSignal) {
	    ICommand command = commandMap.get(electroMagneticSignal);
	    if (command != null) {
		System.out.println("Execute command : " + command);
		command.execute();
	    } else {
		System.out.println("No command found.");
	    }
	}

    }
}