package item03;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
	public byte[] serialize(Object instance) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (bos; ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(instance);
		} catch (Exception e) {
			System.err.printf("예외 발생: %s%n", e);
			return null;
		}
		return bos.toByteArray();
	}

	public Object deserialize(byte[] serializedData) {
		ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
		try (bis; ObjectInputStream ois = new ObjectInputStream(bis)) {
			return ois.readObject();
		} catch (Exception e) {
			System.err.printf("예외 발생: %s%n", e);
		}
		return null;
	}
}
