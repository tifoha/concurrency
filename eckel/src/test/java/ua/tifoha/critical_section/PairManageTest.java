package ua.tifoha.critical_section;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ua.tifoha.IntGeneratorBenchmarkTest;

/**
 * Created by Vitaliy Sereda on 26.08.17.
 */
public class PairManageTest {
	@Rule
	public ConcurrentRule concurrentRule = new ConcurrentRule();

	@Rule
	public RepeatingRule repeatingRule = new RepeatingRule();

	private static PairManager unsynchronysedPairManager = new UnsynchronysedPairManager();
	private static PairManager synchronysedPairManager = new SynchronysedPairManager();
	private static PairManager enhancedPairManager = new EnhancedPairManager();

	@Test
	@Concurrent
	@Repeating
	public void unsynchronysedPairManager() throws Exception {
		final Pair pair = unsynchronysedPairManager.increment();
		assertTrue(pair + " is not valid", pair.isValid());
	}

	@Test
	@Concurrent
	@Repeating
	public void synchronysedPairManager() throws Exception {
		final Pair pair = synchronysedPairManager.increment();
		assertTrue(pair + " is not valid", pair.isValid());
	}

	@Test
	@Concurrent
	@Repeating
	public void enhancedPairManager() throws Exception {
		final Pair pair = enhancedPairManager.increment();
		assertTrue(pair + " is not valid", pair.isValid());
	}
}