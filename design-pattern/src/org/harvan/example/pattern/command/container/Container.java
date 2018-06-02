package org.harvan.example.pattern.command.container;

import org.harvan.example.pattern.command.receiver.Receiver;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public interface Container {
    public void registerReceiver(Receiver receiver);

    public void unRegisterReceiver(Receiver receiver);
}