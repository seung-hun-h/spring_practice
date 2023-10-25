package com.example.cleancode.clientserver.nonthread.v2;

public class ThreadPerRequestScheduler implements ClientScheduler {
	@Override
	public void schedule(ClientRequestProcessor clientRequestProcessor) {
		Thread thread = new Thread(clientRequestProcessor::process);
		thread.start();
	}
}
