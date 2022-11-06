package item37;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

import item34.Planet;

public class Plant {
	enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }

	final String name;
	final LifeCycle lifeCycle;

	public Plant(String name, LifeCycle lifeCycle) {
		this.name = name;
		this.lifeCycle = lifeCycle;
	}

	@Override
	public String toString() {
		return name;
	}

	public static void main(String[] args) {
		Plant[] garden = {
			new Plant("Basil",    LifeCycle.ANNUAL),
			new Plant("Carroway", LifeCycle.BIENNIAL),
			new Plant("Dill",     LifeCycle.ANNUAL),
			new Plant("Lavendar", LifeCycle.PERENNIAL),
			new Plant("Parsley",  LifeCycle.BIENNIAL),
			new Plant("Rosemary", LifeCycle.PERENNIAL)
		};

		// Using ordinal() to index into an array - DON'T DO THIS!  (Page 171)
		Set<Plant>[] plantsByLifeCycleArr = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
		for (int i = 0; i < plantsByLifeCycleArr.length; i++)
			plantsByLifeCycleArr[i] = new HashSet<>();
		for (Plant p : garden)
			plantsByLifeCycleArr[p.lifeCycle.ordinal()].add(p);
		// Print the results
		for (int i = 0; i < plantsByLifeCycleArr.length; i++) {
			System.out.printf("%s: %s%n",
				Plant.LifeCycle.values()[i], plantsByLifeCycleArr[i]);
		}

		EnumMap<LifeCycle, Set<Plant>> plantByLifeCycle  = new EnumMap<>(LifeCycle.class);
		for (LifeCycle lc : LifeCycle.values()) {
			plantByLifeCycle.put(lc, new HashSet<>());
		}

		for (Plant plant : garden) {
			plantByLifeCycle.get(plant.lifeCycle).add(plant);
		}

		System.out.println(plantByLifeCycle);

		System.out.println(Arrays.stream(garden)
			.collect(groupingBy(p -> p.lifeCycle)));

		System.out.println(Arrays.stream(garden)
			.collect(groupingBy(p -> p.lifeCycle,
				() -> new EnumMap<>(LifeCycle.class), toSet()
			)));
	}
}
