package hello.external;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import lombok.extern.slf4j.Slf4j;

/**
 * java -jar abc.jar --username=name --password=1234 --email=abc@naver.com
 * */
@Slf4j
public class CommandLineV2 {
	public static void main(String[] args) {
		for (String arg : args) {
			log.info(arg);
		}

		ApplicationArguments arguments = new DefaultApplicationArguments(args);
		log.info("SourceArgs = {}", Arrays.toString(arguments.getSourceArgs()));

		for (String optionName : arguments.getOptionNames()) {
			log.info("Option args {}={}", optionName, arguments.getOptionValues(optionName));
		}
	}
}
