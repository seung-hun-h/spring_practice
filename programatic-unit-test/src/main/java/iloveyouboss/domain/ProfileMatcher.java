package iloveyouboss.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

import util.Match;

public class ProfileMatcher {
	private static final int DEFAULT_POOL_SIZE = 4;
	private ExecutorService executor = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);
	private Map<String, Profile> profiles = new HashMap<>();

	public void add(Profile profile) {
		profiles.put(profile.getId(), profile);
	}

	public void findMatchingProfiles(
		MatchListener listener,
		List<MatchSet> matchSets,
		BiConsumer<MatchListener, MatchSet> processFunction
	) {
		for (MatchSet set: matchSets) {
			executor.execute(() -> processFunction.accept(listener, set));
		}
		executor.shutdown();
	}

	public void findMatchingProfiles(Criteria criteria, MatchListener listener) {
		findMatchingProfiles(listener, collectMatchSets(criteria), this::process);
	}

	void process(MatchListener listener, MatchSet set) {
		if (set.matches())
			listener.foundMatch(profiles.get(set.getProfileId()), set);
	}

	List<MatchSet> collectMatchSets(Criteria criteria) {
		return profiles.values().stream()
			.map(profile -> profile.getMatchSet(criteria))
			.toList();
	}

	ExecutorService getExecutor() {
		return executor;
	}
}

