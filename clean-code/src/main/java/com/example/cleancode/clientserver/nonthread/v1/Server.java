package com.example.cleancode.clientserver.nonthread.v1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.example.cleancode.clientserver.common.MessageUtils;

public class Server implements Runnable {
	ServerSocket serverSocket;
	volatile boolean keepProcessing = true;

	public Server(int port, int millisecondsTimeout) throws IOException {
		this.serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(millisecondsTimeout);
	}

	@Override
	public void run() {
		System.out.println("Server Starting");

		while (keepProcessing) {
			try {
				System.out.println("accepting client");
				Socket socket = serverSocket.accept();
				System.out.println("get client");
				process(socket);
			} catch (Exception e) {
				handle(e);
			}
		}
	}

	private void handle(Exception exception) {
		if (!(exception instanceof SocketException)) {
			exception.printStackTrace();
		}
	}

	private void process(Socket socket) {
		if (socket == null) {
			return;
		}

		Runnable clientHandler = () -> {
			try {
				System.out.println("Server: getting message");
				String message = MessageUtils.getMessage(socket);
				System.out.printf("Server: got message: %s\n", message);
				Thread.sleep(1000);
				System.out.printf("Server: sending reply: %s\n", message);
				MessageUtils.sendMessage(socket, "Processed: " + message);
				System.out.println("Server: sent");
				closeIgnoreException(socket);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		Thread clientConnection = new Thread(clientHandler);
		clientConnection.start();
	}

	private void closeIgnoreException(Socket socket) {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException ignore) {
			}
		}
	}

	public void stopProcessing() {
		keepProcessing = false;
		closeIgnoreException(serverSocket);
	}

	private void closeIgnoreException(ServerSocket serverSocket) {
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException ignore) {
			}
		}
	}
}
