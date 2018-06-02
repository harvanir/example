package com.javacodegeeks.patterns.commandpattern;

public class TestCommandPattern {
	public static void main(String[] args) {
		init();
	}

	private static void init() {
		ThreadPool pool = new ThreadPool(10);

		Email email;
		EmailJob emailJob = new EmailJob();

		Sms sms;
		SmsJob smsJob = new SmsJob();

		FileIO fileIO;
		FileIOJob fileIOJob = new FileIOJob();

		Logging logging;
		LoggingJob loggingJob = new LoggingJob();

		for (int i = 0; i < 5; i++) {
			email = new Email();
			emailJob.setEmail(email);

			sms = new Sms();
			smsJob.setSms(sms);

			fileIO = new FileIO();
			fileIOJob.setFileIO(fileIO);

			logging = new Logging();
			loggingJob.setLogging(logging);

			pool.addJob(emailJob);
			pool.addJob(smsJob);
			pool.addJob(fileIOJob);
			pool.addJob(loggingJob);
		}

		pool.shutdownPool();
	}
}