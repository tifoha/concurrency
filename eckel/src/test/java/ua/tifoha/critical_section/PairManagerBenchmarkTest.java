package ua.tifoha.critical_section;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

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
public class PairManagerBenchmarkTest {
	private static PairManager unsynchronysedPairManager = new UnsynchronysedPairManager();
	private static PairManager synchronysedPairManager = new SynchronysedPairManager();
	private static PairManager enhancedPairManager = new EnhancedPairManager();

	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void unsynchronizedIntGenerator() {
		Pair value = unsynchronysedPairManager.increment();
	}

	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void synchronysedPairManager() {
		Pair value = synchronysedPairManager.increment();
	}

	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void enhancedPairManager() {
		Pair value = enhancedPairManager.increment();
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(PairManagerBenchmarkTest.class.getSimpleName())
//				.measurementTime(TimeValue.milliseconds(1))
				.measurementIterations(3)
				.warmupIterations(3)
				.forks(1)
				.threads(10)
				.build();

		new Runner(opt).run();
	}

}