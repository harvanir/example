package org.harvan.example.pattern.command.builder;

import org.harvan.example.pattern.command.RemoteControl;
import org.harvan.example.pattern.command.container.Container;
import org.harvan.example.pattern.command.media.ElectroMagneticSignal;
import org.harvan.example.pattern.command.transmitter.Transmitter;
import org.harvan.example.pattern.command.transmitter.UniversalTransmitterStrategy;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public abstract class RemoteControlBuilder {
    private Container container;
    protected Transmitter transmitter;
    protected ElectroMagneticSignal transmissionMedia;

    public RemoteControlBuilder(Container container) {
	this.container = container;
	createTransmitter();
	createTransmissionMedia();
    }

    private void createTransmitter() {
	transmitter = new UniversalTransmitterStrategy("Remote control transmitter", container);
    }

    public abstract void createTransmissionMedia();

    public abstract RemoteControl build();
}