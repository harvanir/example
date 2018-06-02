package org.harvan.example.pattern.command.media;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public class InfraRed implements ElectroMagneticSignal {
    private String data;

    public String getData() {
	return data;
    }

    @Override
    public void setData(String data) {
	this.data = data;
    }

    @Override
    public String toString() {
	return "InfraRed [data=" + data + "]";
    }
}