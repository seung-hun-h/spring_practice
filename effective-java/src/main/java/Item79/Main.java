package Item79;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		// case1();
		// case2();
		case3();
	}

	private static void case2() {
		ObservableSet<Integer> observableSet = new ObservableSet<>(new HashSet<>());

		observableSet.addObserver(new SetObserver<>() {
			@Override
			public void added(ObservableSet<Integer> set, Integer element) {
				System.out.println("element = " + element);

				if (element == 23) {
					set.removeObserver(this); // ConcurrentModificationException
				}
			}
		});

		for (int i = 0; i < 100; i++) {
			observableSet.add(i);
		}
	}

	private static void case3() {
		ObservableSet<Integer> observableSet = new ObservableSet<>(new HashSet<>());

		observableSet.addObserver(new SetObserver<>() {
			@Override
			public void added(ObservableSet<Integer> set, Integer element) {
				System.out.println("element = " + element);

				if (element == 23) {
					ExecutorService executorService = Executors.newSingleThreadExecutor();
					try {
						executorService.submit(() -> set.removeObserver(this)).get();
					} catch (ExecutionException | InterruptedException exception) {
						throw new AssertionError(exception);
					} finally {
						executorService.shutdown();
					}
				}
			}
		});

		for (int i = 0; i < 100; i++) {
			observableSet.add(i);
		}
	}

	private static void case1() {
		ObservableSet<Object> observableSet = new ObservableSet<>(new HashSet<>());

		observableSet.addObserver((s, e) -> System.out.println("e = " + e));

		for (int i = 0; i < 100; i++) {
			observableSet.add(i);
		}
	}
}
