package ua.tifoha;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vitaliy Sereda on 22.08.17.
 */
public class IntGeneratorTester {
	private final int threadCount;
	private IntGenerator intGenerator;
	private ExecutorService exec;

	public IntGeneratorTester(IntGenerator intGenerator, int threadCount) {
		this.intGenerator = intGenerator;
		exec = Executors.newFixedThreadPool(threadCount);
		this.threadCount = threadCount;
	}

	public void test(long timeout, TimeUnit timeUnit) throws InterruptedException {
		for (int i = 0; i < threadCount; i++) {
			exec.submit(()->{
				while (!intGenerator.isCanceled()) {
					int value = intGenerator.next();
					if (value % 2 != 0) {
						intGenerator.cancel();
						System.err.printf("%s - value: '%s' is not even!%n", Thread.currentThread(), value);
					}
					System.out.printf("%s - value: '%s'%n", Thread.currentThread(), value);
				}
			});
		}
		exec.shutdown();
		exec.awaitTermination(timeout, timeUnit);
	}

	public void test10Sec() throws InterruptedException {
		test(10, TimeUnit.SECONDS);
	}
}
//https://xn--d1aiahil.xn--j1amh/assets/components/gallery/connector.php?action=web/phpthumb&ctx=web&w=300&h=200&zc=1&far=C&q=90&src=/assets/gallery/23/206.jpg