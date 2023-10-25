package com.example.cleancode.clientserver.nonthread.v2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorClientScheduler implements ClientScheduler {
	Executor executor;

	public ExecutorClientScheduler(int availableThreads) {
		executor = Executors.newFixedThreadPool(availableThreads);
	}

	@Override
	public void schedule(ClientRequestProcessor clientRequestProcessor) {
		executor.execute(clientRequestProcessor::process);
	}
}
