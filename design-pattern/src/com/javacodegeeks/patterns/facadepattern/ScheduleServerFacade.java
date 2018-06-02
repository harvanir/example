package com.javacodegeeks.patterns.facadepattern;

public class ScheduleServerFacade {
	private final ScheduleServer scheduleServer;

	public ScheduleServerFacade(ScheduleServer scheduleServer) {
		this.scheduleServer = scheduleServer;
	}

	public void startServer() {
		scheduleServer.startBooting();
		scheduleServer.readSystemConfigFile();
		scheduleServer.init();
		scheduleServer.initializeListener();
		scheduleServer.initializeContext();
		scheduleServer.createSystemObjects();
	}

	public void stopServer() {

	}
}