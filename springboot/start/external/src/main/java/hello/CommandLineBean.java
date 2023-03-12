package hello;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommandLineBean {

	private final ApplicationArguments arguments;

	public CommandLineBean(ApplicationArguments arguments) {
		this.arguments = arguments;
	}

	@PostConstruct
	public void init() {
		log.info("SourceArgs {}", arguments.getSourceArgs());
		log.info("NonOptionArgs {}", arguments.getNonOptionArgs());
		for (String optionName : arguments.getOptionNames()) {
			log.info("OptionArgs {}={}", optionName, arguments.getOptionValues(optionName));
		}
	}
}
