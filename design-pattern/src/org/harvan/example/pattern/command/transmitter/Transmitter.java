package org.harvan.example.pattern.command.transmitter;

import org.harvan.example.pattern.command.media.ElectroMagneticSignal;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public interface Transmitter {
    public void transmit(ElectroMagneticSignal electroMagneticSignal);
}