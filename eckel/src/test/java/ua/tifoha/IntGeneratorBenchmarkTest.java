package ua.tifoha;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by Vitaliy Sereda on 25.08.17.
 */
public class IntGeneratorBenchmarkTest {
	private static IntGenerator unsynchronizedIntGenerator = new UnsynchronizedIntGenerator();

	private static IntGenerator synchronizedIntGenerator = new SynchronizedIntGenerator();

	private static IntGenerator atomicIntGenerator = new AtomicIntGenerator();

	private static IntGenerator volatileIntGenerator = new VolatileIntGenerator();

	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void unsynchronizedIntGenerator() {
		int value = unsynchronizedIntGenerator.next();
	}

	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void synchronizedIntGenerator() {
		int value = synchronizedIntGenerator.next();
	}

	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void atomicIntGenerator() {
		int value = atomicIntGenerator.next();
	}

	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void volatileIntGenerator() {
		int value = volatileIntGenerator.next();
	}


	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(IntGeneratorBenchmarkTest.class.getSimpleName())
//				.measurementTime(TimeValue.milliseconds(1))
				.measurementIterations(5)
				.warmupIterations(5)
				.forks(1)
//				.threads(10)
				.build();

		new Runner(opt).run();
	}
}
