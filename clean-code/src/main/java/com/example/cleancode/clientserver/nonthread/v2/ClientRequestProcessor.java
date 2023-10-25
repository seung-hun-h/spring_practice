package com.example.cleancode.clientserver.nonthread.v2;

import java.io.IOException;

public class ClientRequestProcessor {
	private ClientConnection clientConnection;

	public ClientRequestProcessor(ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}

	public void process() {
		try {
			System.out.println("Server: getting message");
			String message = clientConnection.getMessage();
			System.out.printf("Server: got message: %s\n", message);
			Thread.sleep(1000);
			System.out.printf("Server: sending reply: %s\n", message);
			clientConnection.sendMessage("Processed: " + message);
			System.out.println("Server: sent");
			clientConnection.close();
		} catch (IOException | InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
