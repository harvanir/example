package org.harvan.example.pattern.command.transmitter;

import org.harvan.example.pattern.command.container.Container;
import org.harvan.example.pattern.command.container.ElectroMagnetic;
import org.harvan.example.pattern.command.media.ElectroMagneticSignal;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public class UniversalTransmitterStrategy implements Transmitter {
    private String name;
    private ElectroMagnetic electroMagnetic;

    public UniversalTransmitterStrategy(String name, Container container) {
	this.name = name;
	if (container instanceof ElectroMagnetic) {
	    this.electroMagnetic = (ElectroMagnetic) container;
	} else {
	    throw new RuntimeException("Transmission container not supported.");
	}
    }

    @Override
    public void transmit(ElectroMagneticSignal electroMagneticSignal) {
	System.out.println(name + " [ElectroMagneticSignal] = " + electroMagneticSignal);
	electroMagnetic.addWave(electroMagneticSignal);
    }

    @Override
    public String toString() {
	return "UniversalTransmitterStrategy [name=" + name + ", electroMagnetic=" + electroMagnetic + "]";
    }
}