package org.harvan.example.pattern.command.receiver;

import org.harvan.example.pattern.command.media.ElectricalVoltage;
import org.harvan.example.pattern.command.media.ElectroMagneticSignal;
import org.harvan.example.pattern.command.transmitter.Transmitter;

public class DigitalToDigitalReceiverStrategy extends AbstractReceiverStrategy {
    public DigitalToDigitalReceiverStrategy(String name, Transmitter transmitter) {
	super(name, transmitter);
    }

    @Override
    ElectroMagneticSignal internalReceiver(ElectroMagneticSignal transmissionMedia) {
	if (transmissionMedia instanceof ElectricalVoltage) {
	    return transmissionMedia;
	} else {
	    System.out.println("Skiped. Not a digital signal.");
	    return null;
	}
    }
}