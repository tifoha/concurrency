package ua.tifoha;

import static junit.framework.TestCase.assertFalse;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Vitaliy Sereda on 25.08.17.
 */
public class IdGeneratorTest {
	@Rule
	public ConcurrentRule concurrentRule = new ConcurrentRule();

	@Rule
	public RepeatingRule repeatingRule = new RepeatingRule();

	private Collection<Integer> circularList = Collections.synchronizedCollection(new CircularList<>(10000));

	private IntGenerator unsynchronizedIntGenerator = new UnsynchronizedIntGenerator();

	private IntGenerator synchronizedIntGenerator = new SynchronizedIntGenerator();

	private IntGenerator atomicIntGenerator = new AtomicIntGenerator();


	@Test
	@Concurrent
	@Repeating
	public void unsynchronizedIntGenerator() throws Exception {
		int id = unsynchronizedIntGenerator.next();
		assertFalse(id + " is in Set", circularList.contains(id));
		circularList.add(id);
	}

	@Test
	@Concurrent
	@Repeating
	public void synchronizedIntGenerator() throws Exception {
		int id = synchronizedIntGenerator.next();
		assertFalse(id + " is in Set", circularList.contains(id));
		circularList.add(id);
	}

	@Test
	@Concurrent
	@Repeating
	public void atomicIntGenerator() throws Exception {
		int id = atomicIntGenerator.next();
		assertFalse(id + " is in Set", circularList.contains(id));
		circularList.add(id);
	}


	@Test
	public void timerTest() throws Exception {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			final int timeout = rnd.nextInt(10);
			exec.submit(() -> {
				System.out.printf("%s start operation: %d%n", Thread.currentThread(), timeout);
				TimeUnit.SECONDS.sleep(timeout);
				System.out.printf("%s finish%n", Thread.currentThread());
				return null;
			});
		}
		exec.shutdown();
		TimeUnit.SECONDS.sleep(11);
	}


}
