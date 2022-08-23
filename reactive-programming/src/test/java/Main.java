import java.util.Arrays;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;

public class Main {
	public static void main(String[] args) {
		// getTemperatures("New York").subscribe(new TempSubscriber());
		// getCelsiusTemperatures("New York").subscribe(new TempSubscriber());
		Observable<TempInfo> observable = getCelsiusTemperatures("New York", "Chicago", "San Francisco");
		observable.blockingSubscribe(new TempObserver());
	}

	private static Observable<TempInfo> getTemperatures(String town) {
		return Observable.create(emitter -> {
			Observable.interval(1, TimeUnit.SECONDS)
				.subscribe(i -> {
					if (!emitter.isDisposed()) {
						if (i >= 5) {
							emitter.onComplete();
						} else {
							try {
								emitter.onNext(TempInfo.fetch(town));
							} catch (Exception exception) {
								emitter.onError(exception);
							}
						}
					}
				});
		});
	}

	public static Observable<TempInfo> getCelsiusTemperatures(String town) {
		return getTemperatures(town)
			.map(tempInfo -> new TempInfo(tempInfo.getTown(), (tempInfo.getTemp() - 32) * 5 / 9));
	}

	public static Observable<TempInfo> getCelsiusTemperatures(String... town) {
		return Observable.merge(Arrays.stream(town)
			.map(Main::getTemperatures)
			.collect(Collectors.toList()));
	}
}
