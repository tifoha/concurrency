package ua.tifoha.critical_section;

import static org.junit.Assert.*;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Vitaliy Sereda on 26.08.17.
 */
public class PairManageTest {
	@Rule
	public ConcurrentRule concurrentRule = new ConcurrentRule();

	@Rule
	public RepeatingRule repeatingRule = new RepeatingRule();


	private static PairManager unsynchronizedPairManager = new UnsynchronysedPairManager();
	private static PairManager synchronizedPairManager = new SynchronizedPairManager();
	private static PairManager enhancedPairManager = new EnhancedPairManager();

	@Test
	@Concurrent
	@Repeating
	public void unsynchronysedPairManager() throws Exception {
		final Pair pair = unsynchronizedPairManager.increment();
		assertTrue(pair + " is not valid", pair.isValid());
	}

	@Test
	@Concurrent
	@Repeating
	public void synchronysedPairManager() throws Exception {
		final Pair pair = synchronizedPairManager.increment();
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