package org.harvan.example.pattern.command2.command;

import org.harvan.example.pattern.command2.receiver.division.IDivision;

/**
 * <b>Updated:</b> 03-Sep-2017
 * 
 * @author Harvan Irsyadi
 * @version 1.0
 * @since 1.0
 *
 */
public abstract class AbstractDivisionCommand implements ICommand {
    private IDivision division;

    public AbstractDivisionCommand(IDivision division) {
	this.division = division;
    }

    public IDivision getDivision() {
	return division;
    }
}