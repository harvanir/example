package org.harvan.example.pattern.command.container;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.harvan.example.pattern.command.media.ElectroMagneticSignal;
import org.harvan.example.pattern.command.receiver.Receiver;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public class ElectroMagnetic implements Container {
    private String name;
    private List<ElectroMagneticSignal> electroMagneticSignalList;
    private List<Receiver> receiverStrategyList;

    public ElectroMagnetic(String name) {
	this.name = name;
	electroMagneticSignalList = new CopyOnWriteArrayList<>();
	receiverStrategyList = new CopyOnWriteArrayList<>();
    }

    public void addWave(ElectroMagneticSignal electroMagneticSignal) {
	System.out.println(name + " [wave in] = " + electroMagneticSignal);
	electroMagneticSignalList.add(electroMagneticSignal);
	broadcastWave();
    }

    @Override
    public void registerReceiver(Receiver receiver) {
	receiverStrategyList.add(receiver);
    }

    @Override
    public void unRegisterReceiver(Receiver receiver) {
	receiverStrategyList.remove(receiver);
    }

    public void broadcastWave() {
	// move master transmissionMediaList to buffer
	List<ElectroMagneticSignal> buff = new CopyOnWriteArrayList<>(electroMagneticSignalList);
	electroMagneticSignalList.removeAll(buff);

	for (ElectroMagneticSignal signal : buff) {
	    if (!receiverStrategyList.isEmpty()) {
		for (Receiver receiver : receiverStrategyList) {
		    receiver.receive(signal);
		}
	    } else {
		System.out.println("No receiver found on container.");
	    }
	}
    }

    @Override
    public String toString() {
	return "ElectroMagnetic [electroMagneticSignalList=" + electroMagneticSignalList + ", receiverStrategyList="
		+ receiverStrategyList + "]";
    }
}