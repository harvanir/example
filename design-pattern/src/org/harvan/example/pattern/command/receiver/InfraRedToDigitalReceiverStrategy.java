package org.harvan.example.pattern.command.receiver;

import org.harvan.example.pattern.command.media.ElectricalVoltage;
import org.harvan.example.pattern.command.media.ElectroMagneticSignal;
import org.harvan.example.pattern.command.media.InfraRed;
import org.harvan.example.pattern.command.transmitter.Transmitter;

/**
 * This classes equals as a receiver module.
 * 
 * @author Harvan Irsyadi
 *
 */
public class InfraRedToDigitalReceiverStrategy extends AbstractReceiverStrategy {
    protected InfraRed infraRed;

    public InfraRedToDigitalReceiverStrategy(String name, Transmitter transmitter) {
	super(name, transmitter);
    }

    @Override
    ElectroMagneticSignal internalReceiver(ElectroMagneticSignal transmissionMedia) {
	if (transmissionMedia instanceof InfraRed) {
	    infraRed = (InfraRed) transmissionMedia;

	    return convertSignalToDigital(infraRed);
	} else {
	    System.out.println("Skiped. Not an infra red.");
	    return null;
	}
    }

    private ElectroMagneticSignal convertSignalToDigital(InfraRed infraRed) {
	ElectroMagneticSignal digital = new ElectricalVoltage();
	digital.setData(infraRed.getData());

	return digital;
    }

    @Override
    public String toString() {
	return "InfraRedReceiverStrategy [infraRed=" + infraRed + "]";
    }
}