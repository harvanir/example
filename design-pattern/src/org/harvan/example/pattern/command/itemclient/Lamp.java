package org.harvan.example.pattern.command.itemclient;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public class Lamp implements Item {
    private String brand;
    private String model;

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

    @Override
    public void on() {
	System.out.println("Lamp on.");
    }

    @Override
    public void off() {
	System.out.println("Lamp off.");
    }

    @Override
    public String toString() {
	return "Lamp [brand=" + brand + ", model=" + model + "]";
    }
}