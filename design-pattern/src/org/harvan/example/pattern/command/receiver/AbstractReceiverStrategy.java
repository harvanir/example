package org.harvan.example.pattern.command.receiver;

import org.harvan.example.pattern.command.media.ElectroMagneticSignal;
import org.harvan.example.pattern.command.transmitter.Transmitter;

/**
 * Abstraction of receiver module. Define mandatory process.
 * 
 * @author Harvan Irsyadi
 *
 */
public abstract class AbstractReceiverStrategy implements Receiver {
    private String name;
    private Transmitter transmitter;

    public AbstractReceiverStrategy(String name, Transmitter transmitter) {
	this.name = name;
	this.transmitter = transmitter;
    }

    @Override
    public void receive(ElectroMagneticSignal electroMagneticSignal) {
	System.out.println(name + " [ElectroMagneticSignal] = " + electroMagneticSignal);
	ElectroMagneticSignal signal = internalReceiver(electroMagneticSignal);

	if (signal != null) {
	    invoke(signal);
	}
    }

    abstract ElectroMagneticSignal internalReceiver(ElectroMagneticSignal transmissionMedia);

    private void invoke(ElectroMagneticSignal signal) {
	transmitter.transmit(signal);
    }
}
