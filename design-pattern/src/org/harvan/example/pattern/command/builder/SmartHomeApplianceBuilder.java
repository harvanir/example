package org.harvan.example.pattern.command.builder;

import org.harvan.example.pattern.command.appliance.SmartHomeAppliance;
import org.harvan.example.pattern.command.container.Container;

/**
 * 
 * @author Harvan Irsyadi
 *
 */
public abstract class SmartHomeApplianceBuilder {
    protected Container container;

    public SmartHomeApplianceBuilder(Container container) {
	this.container = container;
    }

    public abstract SmartHomeAppliance build();
}