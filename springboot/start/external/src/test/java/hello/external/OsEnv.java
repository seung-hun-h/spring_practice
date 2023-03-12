package hello.external;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OsEnv {
	public static void main(String[] args) {
		Map<String, String> envs = System.getenv();
		for (Map.Entry<String, String> entry : envs.entrySet()) {
			log.info("env {}={}", entry.getKey(), entry.getValue());
		}
	}
}
