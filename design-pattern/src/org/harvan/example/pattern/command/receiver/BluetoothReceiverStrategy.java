package org.harvan.example.pattern.command.receiver;

import org.harvan.example.pattern.command.media.ElectroMagneticSignal;
import org.harvan.example.pattern.command.transmitter.Transmitter;

public class BluetoothReceiverStrategy extends AbstractReceiverStrategy {

    public BluetoothReceiverStrategy(String name, Transmitter transmitter) {
	super(name, transmitter);
    }

    @Override
    ElectroMagneticSignal internalReceiver(ElectroMagneticSignal transmissionMedia) {
	throw new UnsupportedOperationException();
    }
}