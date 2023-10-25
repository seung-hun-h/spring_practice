package com.example.cleancode.clientserver.nonthread.v2;

import java.io.IOException;
import java.net.Socket;

import com.example.cleancode.clientserver.common.MessageUtils;

public class ClientConnection {
	private Socket socket;

	public ClientConnection(Socket socket) {
		this.socket = socket;
	}

	public String getMessage() throws IOException {
		return MessageUtils.getMessage(socket);
	}

	public void sendMessage(String message) throws IOException {
		MessageUtils.sendMessage(socket, message);
	}

	public void close() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException ignore) {
			}
		}
	}
}
