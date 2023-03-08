package memory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemoryController {
	private final MemoryFinder memoryFinder;

	@GetMapping(value = "/memory")
	public Memory system() {
		Memory memory = memoryFinder.get();
		log.info("memory={}", memory);
		return memory;
	}
}
