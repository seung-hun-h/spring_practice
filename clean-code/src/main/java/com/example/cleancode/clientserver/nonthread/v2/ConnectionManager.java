package com.example.cleancode.clientserver.nonthread.v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager {
	private ServerSocket serverSocket;
	public ConnectionManager(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public ClientConnection awaitClient() throws IOException {
		System.out.println("accepting client");
		Socket socket = serverSocket.accept();
		System.out.println("get client");
		return new ClientConnection(socket);
	}

	public void shutdown() {
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException ignore) {
			}
		}
	}
}
