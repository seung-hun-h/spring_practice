package com.example.cleancode.clientserver.nonthread;

import java.io.IOException;
import java.net.Socket;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.cleancode.clientserver.common.MessageUtils;
import com.example.cleancode.clientserver.nonthread.v1.Server;

class ClientTest {
	private static final int PORT = 8009;
	private static final int TIMEOUT = 2000;

	Server server;
	Thread serverThread;

	@BeforeEach
	public void createServer() throws IOException {
		try {
			Server server = new Server(PORT, TIMEOUT);
			serverThread = new Thread(server);
			serverThread.start();
		} catch (IOException e) {
			e.printStackTrace(System.err);
			throw e;
		}
	}

	@AfterEach
	public void shutdownServer() throws InterruptedException {
		if (server != null) {
			server.stopProcessing();
			serverThread.join();
		}
	}

	class TrivialClient implements Runnable {
		int clientNumber;

		public TrivialClient(int clientNumber) {
			this.clientNumber = clientNumber;
		}

		@Override
		public void run() {
			try {
				connectSendReceive(clientNumber);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Test
	void shouldRunInUnder10Seconds() {
		Assertions.assertTimeout(Duration.ofSeconds(10), () -> {
			Thread[] threads = new Thread[10];

			for (int i = 0; i < threads.length; i++) {
				threads[i] = new Thread(new TrivialClient(i));
				threads[i].start();
			}

			for (Thread thread : threads) {
				thread.join();
			}
		});
	}

	private void connectSendReceive(int clientNumber) throws IOException {
		System.out.printf("Client %2d: connecting\n", clientNumber);
		Socket socket = new Socket("localhost", PORT);
		System.out.printf("Client %2d: sending message\n", clientNumber);
		MessageUtils.sendMessage(socket, Integer.toString(clientNumber));
		System.out.printf("Client %2d: getting reply\n", clientNumber);
		MessageUtils.getMessage(socket);
		System.out.printf("Client %2d: finished\n", clientNumber);
		socket.close();
	}

}
