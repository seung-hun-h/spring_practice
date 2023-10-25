package com.example.cleancode.clientserver.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MessageUtils {
	public static void sendMessage(Socket socket, String message) throws IOException {
		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeUTF(message);
		objectOutputStream.flush();
	}

	public static String getMessage(Socket socket) throws IOException {
		InputStream inputStream = socket.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		return objectInputStream.readUTF();
	}
}
