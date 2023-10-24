package com.example.cleancode.args;

public class Main {
	public static void main(String[] args) {
		try {
			Args arg = new Args("l,p#,d*", args);
			boolean logging = arg.getBoolean('l');
			int port = arg.getInt('p');
			String directory = arg.getString('d');
			executeApplication(logging, port, directory);
		} catch (ArgsException exception) {
			System.out.printf("Argument error: %s\n", exception.getMessage());
		}
	}

	private static void executeApplication(boolean logging, int port, String directory) {
		if (logging) {
			System.out.println("port = " + port);
			System.out.println("directory = " + directory);
		}
	}
}
