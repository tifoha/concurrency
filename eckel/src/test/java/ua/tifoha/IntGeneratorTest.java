package ua.tifoha;

import static org.junit.Assert.assertEquals;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Vitaliy Sereda on 22.08.17.
 */
public class IntGeneratorTest {
	@Rule
	public ConcurrentRule concurrentRule = new ConcurrentRule();

	@Rule
	public RepeatingRule repeatingRule = new RepeatingRule();
	private IntGenerator intGenerator;

	@Before
	public void setUp() throws Exception {
		intGenerator = new UnsynchronizedIntGenerator();
	}

//	@Test
//	public void unsynchronizedTest1() throws Exception {
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		IntGeneratorTester tester = new IntGeneratorTester(new UnsynchronizedIntGenerator(), 10);
//		tester.test10Sec();
//	}

	@Test
	@Concurrent(count = 100)
	@Repeating(repetition = 10000)
	public void unsynchronizedTest2() throws Exception {
		final int value = intGenerator.next();
		assertEquals(value + " should be even", 0, value % 2);
	}
}