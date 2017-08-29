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
public class LockBenchmarkTest {
	public static HeavyObject synchronyzedObject = new SynchronyzedObject();
	public static HeavyObject synchronyzedByObject = new SynchronyzedByObject();
	public static HeavyObject lockedObject = new LockedObject();
	public static HeavyObject rwLockedObject = new RWLockedObject();

	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void synchronyzedObject() {
		synchronyzedObject.set(100, synchronyzedObject.get(100) + 1);
	}

	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void synchronyzedByObject() {
		synchronyzedByObject.set(100, synchronyzedByObject.get(100) + 1);
	}
	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void lockedObject() {
		lockedObject.set(100, lockedObject.get(100) + 1);
	}

	@Benchmark
	@OutputTimeUnit (TimeUnit.MILLISECONDS)
	public void rwLockedObject() {
		rwLockedObject.set(100, rwLockedObject.get(100) + 1);
	}

//	@Benchmark
//	@OutputTimeUnit (TimeUnit.MILLISECONDS)
//	public void atomicIntGenerator() {
//		int value = atomicIntGenerator.next();
//	}
//
//	@Benchmark
//	@OutputTimeUnit (TimeUnit.MILLISECONDS)
//	public void volatileIntGenerator() {
//		int value = volatileIntGenerator.next();
//	}


	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(LockBenchmarkTest.class.getSimpleName())
//				.measurementTime(TimeValue.milliseconds(1))
				.measurementIterations(3)
				.warmupIterations(3)
				.forks(1)
				.threads(10)
				.build();

		new Runner(opt).run();
	}
}
