package hello.external;

import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaSystemProperties {
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		for (Object key : properties.keySet()) {
			log.info("property {}={}", key, properties.getProperty(String.valueOf(key)));
		}
	}
}
