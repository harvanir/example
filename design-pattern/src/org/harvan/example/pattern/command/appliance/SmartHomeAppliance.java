package org.harvan.example.pattern.command.appliance;

import org.harvan.example.pattern.command.container.Container;
import org.harvan.example.pattern.command.receiver.Receiver;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public class SmartHomeAppliance {
    private String brand;
    private String model;
    private String color;
    private Receiver receiver;

    public SmartHomeAppliance(Container container, Receiver receiver, Container internalElectricalContainer,
	    Receiver controllerReceiver) {
	this.receiver = receiver;
	container.registerReceiver(receiver);
	internalElectricalContainer.registerReceiver(controllerReceiver);
    }

    public String getBrand() {
	return brand;
    }

    public void setBrand(String brand) {
	this.brand = brand;
    }

    public String getModel() {
	return model;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public String getColor() {
	return color;
    }

    public void setColor(String color) {
	this.color = color;
    }

    @Override
    public String toString() {
	return "SmartHomeAppliance [brand=" + brand + ", model=" + model + ", color=" + color + ", receiver=" + receiver
		+ "]";
    }
}