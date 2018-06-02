package org.harvan.example.pattern.command.builder;

import org.harvan.example.pattern.command.RemoteControl;
import org.harvan.example.pattern.command.command.ICommand;
import org.harvan.example.pattern.command.container.Container;
import org.harvan.example.pattern.command.media.ElectroMagneticSignal;
import org.harvan.example.pattern.command.media.InfraRed;
import org.harvan.example.pattern.command.transmitter.Transmitter;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public class SmartHomeRemoteControlVer1Builder extends RemoteControlBuilder {
    public SmartHomeRemoteControlVer1Builder(Container electroMagnetic) {
	super(electroMagnetic);
    }

    @Override
    public void createTransmissionMedia() {
	// v1 : InfraRed
	transmissionMedia = new InfraRed();
    }

    @Override
    public RemoteControl build() {
	ICommand onCommand = new SmartHomeLightOnCommand(transmissionMedia, transmitter);
	ICommand offCommand = new SmartHomeLightOffCommand(transmissionMedia, transmitter);

	RemoteControl remoteControl = new RemoteControl(onCommand, offCommand);
	remoteControl.setColor("Black");

	return remoteControl;
    }

    private class SmartHomeLightOffCommand implements ICommand {
	private ElectroMagneticSignal electroMagneticSignal;
	private Transmitter transmitter;

	public SmartHomeLightOffCommand(ElectroMagneticSignal electroMagneticSignal, Transmitter transmitter) {
	    this.electroMagneticSignal = electroMagneticSignal;
	    this.transmitter = transmitter;
	}

	@Override
	public void execute() {
	    electroMagneticSignal.setData("off");
	    transmitter.transmit(electroMagneticSignal);
	}

	@Override
	public void rollback() {
	    electroMagneticSignal.setData("on");
	    transmitter.transmit(electroMagneticSignal);
	}

	@Override
	public String toString() {
	    return "SmartHomeLightOffCommand [electroMagneticSignal=" + electroMagneticSignal + ", transmitter="
		    + transmitter + "]";
	}
    }

    class SmartHomeLightOnCommand implements ICommand {
	private ElectroMagneticSignal electroMagneticSignal;
	private Transmitter transmitter;

	public SmartHomeLightOnCommand(ElectroMagneticSignal electroMagneticSignal, Transmitter transmitter) {
	    this.electroMagneticSignal = electroMagneticSignal;
	    this.transmitter = transmitter;
	}

	@Override
	public void execute() {
	    electroMagneticSignal.setData("on");
	    transmitter.transmit(electroMagneticSignal);
	}

	@Override
	public void rollback() {
	    electroMagneticSignal.setData("off");
	    transmitter.transmit(electroMagneticSignal);
	}

	@Override
	public String toString() {
	    return "SmartHomeLightOnCommand [electroMagneticSignal=" + electroMagneticSignal + ", transmitter="
		    + transmitter + "]";
	}
    }
}