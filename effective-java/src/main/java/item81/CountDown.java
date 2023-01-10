package item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CountDown {
	public static void main(String[] args) throws InterruptedException {
		Executor executor = Executors.newFixedThreadPool(10);
		long time = time(executor, 10, () -> System.out.println(Thread.currentThread().getId()));
		System.out.println("time = " + time);
	}

	private static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
		CountDownLatch ready = new CountDownLatch(concurrency);
		CountDownLatch start = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(concurrency);

		for (int i = 0; i < concurrency; i++) {
			executor.execute(() -> {
				// 타이머에게 준비를 마쳤음을 알린다
				ready.countDown();
				try {
					// 모든 작업자 스레드가 준비될 때까지 기다린다
					start.await();
					// 작업을 시작한다
					action.run();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} finally {
					// 타이머에게 작업을 마쳤음을 알린다
					done.countDown();
				}
			});
		}

		// 모든 작업자가 준비될 때까지 기다린다
		ready.await();
		long startNanos = System.nanoTime();
		// 작업자들을 꺠운다
		start.countDown();
		// 모든 작업자가 일을 마치기까지 기다린다
		done.await();
		return System.nanoTime() - startNanos;
	}
}
