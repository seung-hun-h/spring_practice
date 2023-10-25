package com.example.cleancode.clientserver.nonthread.v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Timer;
import java.util.concurrent.ExecutorService;

import com.example.cleancode.clientserver.common.MessageUtils;

public class ServerV2 implements Runnable {
	volatile boolean keepProcessing = true;
	private ConnectionManager connectionManager;
	private ClientScheduler clientScheduler;

	public ServerV2(int port, int millisecondsTimeout) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(millisecondsTimeout);

		this.connectionManager = new ConnectionManager(serverSocket);
		this.clientScheduler = new ThreadPerRequestScheduler();
	}

	@Override
	public void run() {
		while (keepProcessing) {
			try {
				ClientConnection clientConnection = connectionManager.awaitClient();
				ClientRequestProcessor clientRequestProcessor = new ClientRequestProcessor(clientConnection);
				clientScheduler.schedule(clientRequestProcessor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		connectionManager.shutdown();
	}

	public void stopProcessing() {
		keepProcessing = false;
	}
}
