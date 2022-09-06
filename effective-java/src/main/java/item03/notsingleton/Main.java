package item03.notsingleton;

import item03.Serializer;

public class Main {
	public static void main(String[] args) {
		Serializer serializer = new Serializer();
		Elvis origin = Elvis.getInstance();

		byte[] serialize = serializer.serialize(origin);
		Elvis deserialized = (Elvis)serializer.deserialize(serialize);

		System.out.println(origin == deserialized);
	}
}
