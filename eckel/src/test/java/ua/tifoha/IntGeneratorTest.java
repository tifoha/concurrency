package ua.tifoha;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by Vitaliy Sereda on 22.08.17.
 */
public class IntGeneratorTest {
	@Test
	public void UnsynchronizedTest() throws Exception {
		System.out.println();
		System.out.println();
		System.out.println();
		IntGeneratorTester tester = new IntGeneratorTester(new UnsynchronizedIntGenerator(), 10);
		tester.test10Sec();
	}
}