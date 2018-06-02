package org.harvan.example.pattern.command.receiver;

import org.harvan.example.pattern.command.media.ElectroMagneticSignal;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public interface Receiver {
    public void receive(ElectroMagneticSignal transmissionMedia);
}