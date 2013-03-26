/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.ENamedElementImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.test.core.common.util.URITest;

/**
 * A harness for micro benchmarking.
 * Run this harness with the <code>-XX:+PrintCompilation</code> and <code>-verbose:gc</code>
 * to ensure that you're collecting measurements after JITing as completed and that no garbage collection overhead is affecting the result.
 */
public class BenchmarkHarness
{
  /**
   * Formatting for nano seconds with three decimals of precision.
   */
  protected static final NumberFormat NANO_SECOND_FORMAT = new DecimalFormat("#,##0.000");

  /**
   * Formatting for percentages with two decimals of precision.
   */
  protected static final NumberFormat PERCENT_FORMAT = new DecimalFormat("###0.00");

  /**
   * The number of milliseconds in a second.
   */
  protected static final long MILLI_SECONDS_PER_SECOND = 1000L;

  /**
   * The number of microseconds in a second.
   */
  protected static final long MICRO_SECONDS_PER_SECOND = MILLI_SECONDS_PER_SECOND * 1000L;

  /**
   * The number of nanoseconds in a second.
   */
  protected static final long NANO_SECONDS_PER_SECOND = MICRO_SECONDS_PER_SECOND * 1000L;

  /**
   * The number of picoseconds in a second.
   */
  protected static final long PICO_SECONDS_PER_SECOND = NANO_SECONDS_PER_SECOND * 1000L;

  /**
   * An abstract base class for micro benchmark performance analysis.
   */
  public static abstract class Benchmark
  {
    /**
     * The number of units of work done by {@link #run()}.
     */
    public int count;

    /**
     * The number of times the {@link #run()} must be invoked to perform at least 1 second total execution.
     * This is updated by calibrate {@link BenchmarkHarness#calibrate(Benchmark)}.
     */
    public int repeat = 1;

    /**
     * Creates an instance that will do the indicated number of units of work in {@link #run()}
     * @param count
     */
    public Benchmark(int count)
    {
      this.count = count;
    }

    /**
     * Specialize this method to do {@link #count} units of work.
     * I.e., write a loop like this
     * <pre>
     * int total = 0;
     * for (int i = 0, count = this.count; i < count; ++i)
     * {
     *   total += i;
     * }
     * return total;
     * </pre>
     * Be sure that some result from doing the unit of work, i.e., the body of the loop, affects the result.
     * I.e., here we use the loop variable to compute a sum that we return.
     */
    public abstract int run();

    /**
     * Returns a string representation of the code for the unit of work.
     */
    public abstract String getLogic();

    /**
     * Cleans up the benchmark between measured runs.
     * It's an opportunity to discard information accumulated during the measured executions of {@link #run()}.
     */
    public void reset()
    {
      // Do nothing.
    }
  }

  /**
   * The {@link #accumulateResults() accumulated result} of the {@link #results} at the end of the call to {@link #measure(Benchmark)}.
   */
  protected int accumulatedResult;

  /**
   * The number of seconds each benchmark should run.
   */
  protected int interval;

  /**
   * Creates an instance.
   * @see #run(Benchmark...)}.
   */
  public BenchmarkHarness()
  {
    this(1);
  }

  /**
   * Creates an instance.
   * @see #run(Benchmark...)}.
   */
  public BenchmarkHarness(int interval)
  {
    this.interval = interval;
  }

  /**
   * Returns the average of the benchmark's measurements,
   * optionally printing its statistics,
   * i.e., the minimum, average, maximum, coefficient of variation, and the 95% confidence range.
   */
  protected long summarize(boolean dump, Benchmark benchmark, long[] measurements)
  {
    int measurementCount = measurements.length;

    // Computes the average.
    //
    long total = 0;
    for (int i = 0; i < measurementCount; ++i)
    {
      long measurement = measurements[i];
      total += measurement;
    }
    long average = total / measurementCount;

    // If we're not printing statistics, we can just return now.
    //
    if (!dump)
    {
      return average;
    }

    // Sort the measurements.
    //
    Arrays.sort(measurements);

    // Compute the bounds of the 90% confidence interval.
    // I.e., the range within which 90% of the measurements lie.
    //
    int ci = measurementCount / 20;
    long confidenceRangeLower = measurements[ci];
    long confidenceRangeUpper = measurements[measurementCount - ci];

    // Compute the standard deviation.
    //
    long sum = 0;
    for (int i = 0; i < measurementCount; ++i)
    {
      long measurement = measurements[i];
      long deviation = measurement - average;
      sum += deviation * deviation;
    }
    sum /= (measurementCount - 1);
    long standardDeviation = (long)Math.sqrt(sum);

    // Dump the statistics.
    //
    System.err.println(benchmark.getLogic());
    System.err.print(" ");
    System.err.print(NANO_SECOND_FORMAT.format(measurements[0] / 1000.0));
    System.err.print(" < ");
    System.err.print(NANO_SECOND_FORMAT.format(average / 1000.0));
    System.err.print(" < ");
    System.err.print(NANO_SECOND_FORMAT.format(measurements[measurementCount - 1] / 1000.0));
    System.err.print(" CV%: ");
    System.err.print(PERCENT_FORMAT.format(standardDeviation * 100.0 / average));
    System.err.print(" CR 90%: ");
    System.err.print(NANO_SECOND_FORMAT.format(confidenceRangeLower / 1000.0));
    System.err.print(" <- ");
    System.err.print(NANO_SECOND_FORMAT.format(confidenceRangeUpper / 1000.0));
    System.err.println();
    System.err.flush();

    return average;
  }

  /**
   * Measure the time it takes to execute the benchmark, and increases the {@link Benchmark#repeat} to ensure that it runs for close to a full second.
   * Returns a result derived from the benchmark execution to ensure that the JIT doesn't consider the benchmark execution dead code; it's a meaningless value.
   */
  public int calibrate(Benchmark benchmark)
  {
    int total = 0;
    benchmark.repeat *= 20;

    for (;;)
    {
      benchmark.reset();

      // Determine the elapsed interval for the benchmark.
      //
      long start = System.nanoTime();
      for (int j = 0; j < benchmark.repeat; ++j)
      {
        total += benchmark.run();
      }
      long end = System.nanoTime();
      long elapsedTime = end - start;

      // Determine how many times the benchmark needs to be run in order to take close to a interval number of full seconds.
      //
      double fraction= (double)interval * benchmark.repeat * NANO_SECONDS_PER_SECOND / elapsedTime;

      // Because we call this method recursively, check that the new count is at least 10% bigger than the repetitions we're already using...
      //
      if (fraction > 1.1 * benchmark.repeat)
      {
        // Calibrate again with the new repeat count to ensure that this does achieve a result close to one second.
        //
        benchmark.repeat = (int)fraction + 1;
      }
      else
      {
        break;
      }
    }

    benchmark.reset();

    // Return a meaningless result.
    //
    return total;
  }

  /**
   * Measure the time it takes to execute the running, returning the average per unit time in picoseconds.
   */
  public long measure(Benchmark benchmark)
  {
    // Execute the benchmark repeatedly and determine the elapsed nanosecond time interval for the benchmark.
    // A meaningless result is accumulated to ensure that the JIT doesn't consider the execution to be dead code.
    //
    int repeat = benchmark.repeat;
    int total = 0;
    long start = System.nanoTime();
    for (int j = 0; j < repeat; ++j)
    {
      total += benchmark.run();
    }
    long end = System.nanoTime();
    long elapsedTime = end - start;

    // Validate the benchmark has run long enough (at least 5 seconds) to make the inaccuracy in System.nanoTime() insignificant relative to the interval being measured.
    //
    if (elapsedTime < interval * NANO_SECONDS_PER_SECOND / 2)
    {
      double fraction = (double)interval * NANO_SECONDS_PER_SECOND / elapsedTime;
      throw new RuntimeException("Measurement interval for the benchmark is less than " + interval / 2.0 + " seconds so the benchmark's repeat should have been increased by " + PERCENT_FORMAT.format(fraction) + "%");
    }

    // Accumulate the result into a globally visible value, again to ensure the JIT doesn't decide to stop running the benchmarks.
    //
    accumulatedResult += total;

    benchmark.reset();

    // Covert the time to pico seconds and divide by the benchmark's count and repeat to derive per-unit time.
    //
    return 1000L * elapsedTime / benchmark.count / repeat;
  }

  /**
   * Executes the benchmark for the specified number of repetitions and dumps the measurement statistics.
   */
  public void run(int repetitions, Benchmark benchmark)
  {
    System.out.flush();

    System.err.println("Measuring");
    System.err.println(benchmark.getLogic());

    // Each run is already an average of many benchmark runs.
    //
    long[] averages = new long[repetitions];
    for (int i = 0; i < repetitions; ++i)
    {
      averages[i] = measure(benchmark);
      System.err.print(NANO_SECOND_FORMAT.format(averages[i] / 1000.0));
      System.err.print(" ");
    }

    // Dump statistics about the averages of the averages.
    //
    System.err.println();
    summarize(true, benchmark, averages);
  }

  /**
   * Measure the performance of the given benchmarks,
   * dumping their statistics
   */
  public void run(int count, Benchmark... benchmarks)
  {
    // Calibrate the benchmarks and accumulate meaningless result to circumvent aggressive JIT optimization of dead code.
    //
    for (int i = 0; i < benchmarks.length; ++i)
    {
      accumulatedResult += calibrate(benchmarks[i]);
    }

    // Repeatedly measure the benchmarks and accumulate meaningless result to circumvent aggressive JIT optimization of dead code.
    //
    for (int i = 0; i < 10; ++i)
    {
      for (int j = 0; j < benchmarks.length; ++j)
      {
        run(count, benchmarks[j]);
      }
    }

    // Print the final meaningless result.
    //
    System.out.println("Accumulated result: " + accumulatedResult);
  }

  public static class CountedLoop extends Benchmark
  {
    public CountedLoop()
    {
      super(1000000);
    }

    @Override
    public int run()
    {
      int total = 0;
      for (int i = 0; i < count; ++i)
      {
        total += i;
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i;";
    }
  }

  public static class CountedLoopWithLocalCounter extends Benchmark
  {
    public CountedLoopWithLocalCounter()
    {
      super(1000000);
    }

    @Override
    public int run()
    {
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i;
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i; // With local count";
    }
  }

  public static class CountedLoopWithAdd extends Benchmark
  {
    public CountedLoopWithAdd()
    {
      super(1000000);
    }

    @Override
    public int run()
    {
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + i;
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + i;";
    }
  }


  public static class ArrayReadAtIndex extends Benchmark
  {
    protected int[] array;
    protected Random random = new Random(0);

    public ArrayReadAtIndex()
    {
      super(1000000);

      array = new int[count];
      for (int i = 0, count = this.count; i < count; ++i)
      {
        array[i] = random.nextInt();
      }
    }

    @Override
    public int run()
    {
      int[] array = this.array;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += array[i];
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += array[i];";
    }
  }

  public static class ArrayReadAtIndexAndAddIndex extends Benchmark
  {
    protected int summary;
    protected int[] array;
    protected Random random = new Random(0);

    public ArrayReadAtIndexAndAddIndex()
    {
      super(1000000);
      array = new int[count];
      for (int i = 0, count = this.count; i < count; ++i)
      {
        array[i] = random.nextInt();
      }
    }

    @Override
    public int run()
    {
      int[] array = this.array;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + array[i];
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + array[i];";
    }
  }

  public static class ArrayReadAtIndexAndAddIndexWithArrayLengthGuard extends Benchmark
  {
    protected int summary;
    protected int[] array;
    protected Random random = new Random(0);

    public ArrayReadAtIndexAndAddIndexWithArrayLengthGuard()
    {
      super(1000000);
      array = new int[count];
      for (int i = 0, count = this.count; i < count; ++i)
      {
        array[i] = random.nextInt();
      }
    }

    @Override
    public int run()
    {
      int[] array = this.array;
      int total = 0;
      for (int i = 0; i < array.length; ++i)
      {
        total += i + array[i];
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + array[i]; // With array length guard";
    }
  }

  public static class ArrayReadAtIndexAddTwice extends Benchmark
  {
    protected int[] array;
    protected Random random = new Random(0);

    public ArrayReadAtIndexAddTwice()
    {
      super(1000000);
      array = new int[count];
      for (int i = 0; i < count; ++i)
      {
        array[i] = random.nextInt();
      }
    }

    @Override
    public int run()
    {
      int[] array = this.array;
      int total = 0;
      for (int i = 0; i < count; ++i)
      {
        total += i;
        total += array[i];
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i;\ntotal += array[i];";
    }
  }

  public static class ArrayUpdateIndex extends Benchmark
  {
    protected int[] array;
    protected Random random = new Random(0);

    public ArrayUpdateIndex()
    {
      super(1000000);
      array = new int[count];
      for (int i = 0; i < count; ++i)
      {
        array[i] = random.nextInt();
      }
    }

    @Override
    public int run()
    {
      int[] array = this.array;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        array[i] = total += i + array[i];
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "array[i] = total += array[i];";
    }
  }

  public static class StringArrayHashCode extends Benchmark
  {
    protected String[] array;
    protected Random random = new Random(0);

    public StringArrayHashCode()
    {
      super(1000000);
      array = new String[count];
      for (int i = 0, count = this.count; i < count; ++i)
      {
        array[i] = new String(String.valueOf(random.nextInt()));
      }
    }

    @Override
    public int run()
    {
      String[] array = this.array;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + array[i].hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + array[i].hashCode();";
    }
  }

  public static class ObjectArrayHashCode extends Benchmark
  {
    protected Object[] array;

    public ObjectArrayHashCode()
    {
      super(1000000);
      array = new Object[count];
      for (int i = 0, count = this.count; i < count; ++i)
      {
        array[i] = new Object();
      }
    }

    @Override
    public int run()
    {
      Object[] array = this.array;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + array[i].hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + array[i].hashCode();";
    }
  }

  public static class StringArrayListHashCode extends Benchmark
  {
    protected ArrayList<String> list;
    protected Random random = new Random(0);

    public StringArrayListHashCode()
    {
      super(1000000);
      list = new ArrayList<String>(count);
      for (int i = 0; i < count; ++i)
      {
        list.add(new String(String.valueOf(random.nextInt())));
      }
    }

    @Override
    public int run()
    {
      ArrayList<String> list = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + list.get(i).hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + list.get(i).hashCode();";
    }
  }

  public static class StringArrayListHashCodeWithSizeCall extends Benchmark
  {
    protected ArrayList<String> list;
    protected Random random = new Random(0);

    public StringArrayListHashCodeWithSizeCall()
    {
      super(1000000);
      list = new ArrayList<String>(count);
      for (int i = 0; i < count; ++i)
      {
        list.add(new String(String.valueOf(random.nextInt())));
      }
    }

    @Override
    public int run()
    {
      ArrayList<String> list = this.list;
      int total = 0;
      for (int i = 0; i < list.size(); ++i)
      {
        total += i + list.get(i).hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + list.get(i).hashCode();";
    }
  }

  public static class StringBasicEListHashCode extends Benchmark
  {
    protected BasicEList<String> list;
    protected Random random = new Random(0);

    public StringBasicEListHashCode()
    {
      super(1000000);
      list = new BasicEList<String>(count);
      for (int i = 0; i < count; ++i)
      {
        list.add(new String(String.valueOf(random.nextInt())));
      }
    }

    @Override
    public int run()
    {
      BasicEList<String> eList = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + eList.get(i).hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + eList.get(i).hashCode();";
    }
  }

  public static class StringArrayListHashCodeWithForEachLoop extends Benchmark
  {
    protected List<String> list;
    protected Random random = new Random(0);

    public StringArrayListHashCodeWithForEachLoop()
    {
      super(1000000);
      list = new ArrayList<String>(count);
      for (int i = 0; i < count; ++i)
      {
        list.add(new String(String.valueOf(random.nextInt())));
      }
    }

    @Override
    public int run()
    {
      List<String> list = this.list;
      int total = 0;
      for (String value : list)
      {
        total += value.hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "for (String value : list) {\n  total += value.hashCode();\n}";
    }
  }

  public static class StringBasicEListHashCodeWithForEachLoop extends Benchmark
  {
    protected List<String> list;
    protected Random random = new Random(0);

    public StringBasicEListHashCodeWithForEachLoop()
    {
      super(1000000);
      list = new BasicEList<String>(count);
      for (int i = 0; i < count; ++i)
      {
        list.add(new String(String.valueOf(random.nextInt())));
      }
    }

    @Override
    public int run()
    {
      List<String> eList = this.list;
      int total = 0;
      for (String value : eList)
      {
        total += value.hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "for (String value : eList) {\n  total += value.hashCode();\n}";
    }
  }

  public static class StringArrayCharAt extends Benchmark
  {
    protected String[] array;
    protected Random random = new Random(0);

    public StringArrayCharAt()
    {
      super(1000000);
      array = new String[count];
      for (int i = 0; i < count; ++i)
      {
        array[i] = new String(String.valueOf(random.nextInt()));
      }
    }

    @Override
    public int run()
    {
      String[] array = this.array;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + array[i].charAt(0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + array[i].charAt(0);";
    }
  }

  public static class StringArrayListCharAt extends Benchmark
  {
    protected ArrayList<String> list;
    protected Random random = new Random(0);

    public StringArrayListCharAt()
    {
      super(1000000);
      list = new ArrayList<String>(count);
      for (int i = 0; i < count; ++i)
      {
        list.add(new String(String.valueOf(random.nextInt())));
      }
    }

    @Override
    public int run()
    {
      ArrayList<String> list = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + list.get(i).charAt(0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + list.get(i).charAt(0);";
    }
  }

  public static class ENamedElementArrayGetName extends Benchmark
  {
    protected ENamedElement[] array;
    protected Random random = new Random(0);

    public ENamedElementArrayGetName()
    {
      super(1000000);
      array = new ENamedElement[count];
      for (int i = 0; i < count; ++i)
      {
        switch (random.nextInt(10))
        {
          case 0:
          {
            array[i] = EcoreFactory.eINSTANCE.createEAttribute();
            break;
          }
          case 1:
          {
            array[i] = EcoreFactory.eINSTANCE.createEClass();
            break;
          }
          case 2:
          {
            array[i] = EcoreFactory.eINSTANCE.createEDataType();
            break;
          }
          case 3:
          {
            array[i] = EcoreFactory.eINSTANCE.createEEnum();
            break;
          }
          case 4:
          {
            array[i] = EcoreFactory.eINSTANCE.createEEnumLiteral();
            break;
          }
          case 5:
          {
            array[i] = EcoreFactory.eINSTANCE.createEOperation();
            break;
          }
          case 6:
          {
            array[i] = EcoreFactory.eINSTANCE.createEPackage();
            break;
          }
          case 7:
          {
            array[i] = EcoreFactory.eINSTANCE.createEParameter();
            break;
          }
          case 8:
          {
            array[i] = EcoreFactory.eINSTANCE.createEReference();
            break;
          }
          case 9:
          {
            array[i] = EcoreFactory.eINSTANCE.createETypeParameter();
            break;
          }
        }
        array[i].setName("_" + random.nextInt());
      }
    }

    @Override
    public int run()
    {
      ENamedElement[] array = this.array;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + array[i].getName().hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + array[i].getName().hashCode();";
    }
  }

  public static class ENamedElementArrayListGetName extends Benchmark
  {
    protected List<ENamedElement> list;
    protected Random random = new Random(0);

    public ENamedElementArrayListGetName()
    {
      super(1000000);
      list = new ArrayList<ENamedElement>(count);
      for (int i = 0; i < count; ++i)
      {
        switch (random.nextInt(10))
        {
          case 0:
          {
            list.add(EcoreFactory.eINSTANCE.createEAttribute());
            break;
          }
          case 1:
          {
            list.add(EcoreFactory.eINSTANCE.createEClass());
            break;
          }
          case 2:
          {
            list.add(EcoreFactory.eINSTANCE.createEDataType());
            break;
          }
          case 3:
          {
            list.add(EcoreFactory.eINSTANCE.createEEnum());
            break;
          }
          case 4:
          {
            list.add(EcoreFactory.eINSTANCE.createEEnumLiteral());
            break;
          }
          case 5:
          {
            list.add(EcoreFactory.eINSTANCE.createEOperation());
            break;
          }
          case 6:
          {
            list.add(EcoreFactory.eINSTANCE.createEPackage());
            break;
          }
          case 7:
          {
            list.add(EcoreFactory.eINSTANCE.createEParameter());
            break;
          }
          case 8:
          {
            list.add(EcoreFactory.eINSTANCE.createEReference());
            break;
          }
          case 9:
          {
            list.add(EcoreFactory.eINSTANCE.createETypeParameter());
            break;
          }
        }
        list.get(i).setName("_" + random.nextInt());
      }
    }

    @Override
    public int run()
    {
      List<ENamedElement> list = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + list.get(i).getName().hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + list.get(i).getName().hashCode();";
    }
  }

  public static class EModelElementArrayListGetName extends Benchmark
  {
    protected List<Object> list;
    protected Random random = new Random(0);

    public EModelElementArrayListGetName()
    {
      super(1000000);
      list = new ArrayList<Object>(count);
      for (int i = 0; i < count; ++i)
      {
        switch (random.nextInt(10))
        {
          case 0:
          {
            list.add(EcoreFactory.eINSTANCE.createEAttribute());
            break;
          }
          case 1:
          {
            list.add(EcoreFactory.eINSTANCE.createEClass());
            break;
          }
          case 2:
          {
            list.add(EcoreFactory.eINSTANCE.createEDataType());
            break;
          }
          case 3:
          {
            list.add(EcoreFactory.eINSTANCE.createEEnum());
            break;
          }
          case 4:
          {
            list.add(EcoreFactory.eINSTANCE.createEEnumLiteral());
            break;
          }
          case 5:
          {
            list.add(EcoreFactory.eINSTANCE.createEOperation());
            break;
          }
          case 6:
          {
            list.add(EcoreFactory.eINSTANCE.createEPackage());
            break;
          }
          case 7:
          {
            list.add(EcoreFactory.eINSTANCE.createEParameter());
            break;
          }
          case 8:
          {
            list.add(EcoreFactory.eINSTANCE.createEReference());
            break;
          }
          case 9:
          {
            list.add(EcoreFactory.eINSTANCE.createETypeParameter());
            break;
          }
        }
        ((ENamedElement)list.get(i)).setName("_" + random.nextInt());
      }
    }

    @Override
    public int run()
    {
      List<Object> list = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + ((ENamedElement)list.get(i)).getName().hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + ((ENamedElement)list.get(i)).getName().hashCode();";
    }
  }

  public static class ENamedElementArrayListContains extends Benchmark
  {
    protected List<ENamedElement> list;
    protected ENamedElement lastENamedElement;
    protected Random random = new Random(0);

    public ENamedElementArrayListContains()
    {
      super(10000);
      list = new ArrayList<ENamedElement>(count);
      for (int i = 0; i < count; ++i)
      {
        switch (random.nextInt(10))
        {
          case 0:
          {
            list.add(EcoreFactory.eINSTANCE.createEAttribute());
            break;
          }
          case 1:
          {
            list.add(EcoreFactory.eINSTANCE.createEClass());
            break;
          }
          case 2:
          {
            list.add(EcoreFactory.eINSTANCE.createEDataType());
            break;
          }
          case 3:
          {
            list.add(EcoreFactory.eINSTANCE.createEEnum());
            break;
          }
          case 4:
          {
            list.add(EcoreFactory.eINSTANCE.createEEnumLiteral());
            break;
          }
          case 5:
          {
            list.add(EcoreFactory.eINSTANCE.createEOperation());
            break;
          }
          case 6:
          {
            list.add(EcoreFactory.eINSTANCE.createEPackage());
            break;
          }
          case 7:
          {
            list.add(EcoreFactory.eINSTANCE.createEParameter());
            break;
          }
          case 8:
          {
            list.add(EcoreFactory.eINSTANCE.createEReference());
            break;
          }
          case 9:
          {
            list.add(EcoreFactory.eINSTANCE.createETypeParameter());
            break;
          }
        }
        (lastENamedElement = list.get(i)).setName("_" + random.nextInt());
      }
    }

    @Override
    public int run()
    {
      List<ENamedElement> list = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (list.contains(lastENamedElement) ? 1 : 0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + (list.contains(lastENamedElement) ? 1 : 0);";
    }
  }

  public static class ENamedElementFastEListContains extends Benchmark
  {
    protected BasicEList.FastCompare<ENamedElement> list;
    protected ENamedElement lastENamedElement;
    protected Random random = new Random(0);

    public ENamedElementFastEListContains()
    {
      super(1000);
      list = new BasicEList.FastCompare<ENamedElement>(count);
      for (int i = 0; i < count; ++i)
      {
        switch (random.nextInt(10))
        {
          case 0:
          {
            list.add(EcoreFactory.eINSTANCE.createEAttribute());
            break;
          }
          case 1:
          {
            list.add(EcoreFactory.eINSTANCE.createEClass());
            break;
          }
          case 2:
          {
            list.add(EcoreFactory.eINSTANCE.createEDataType());
            break;
          }
          case 3:
          {
            list.add(EcoreFactory.eINSTANCE.createEEnum());
            break;
          }
          case 4:
          {
            list.add(EcoreFactory.eINSTANCE.createEEnumLiteral());
            break;
          }
          case 5:
          {
            list.add(EcoreFactory.eINSTANCE.createEOperation());
            break;
          }
          case 6:
          {
            list.add(EcoreFactory.eINSTANCE.createEPackage());
            break;
          }
          case 7:
          {
            list.add(EcoreFactory.eINSTANCE.createEParameter());
            break;
          }
          case 8:
          {
            list.add(EcoreFactory.eINSTANCE.createEReference());
            break;
          }
          case 9:
          {
            list.add(EcoreFactory.eINSTANCE.createETypeParameter());
            break;
          }
        }
        (lastENamedElement = list.get(i)).setName("_" + random.nextInt());
      }
    }

    @Override
    public int run()
    {
      BasicEList.FastCompare<ENamedElement> eList = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (eList.contains(lastENamedElement) ? 1 : 0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + (eList.contains(lastENamedElement) ? 1 : 0); // Fast compare";
    }
  }

  public static class ENamedElementContainmentEListContains extends Benchmark
  {
    protected EObjectContainmentEList<ENamedElement> list;
    protected ENamedElement lastENamedElement;
    protected Random random = new Random(0);

    @SuppressWarnings("unchecked")
    public ENamedElementContainmentEListContains()
    {
      super(1000);
      list = (EObjectContainmentEList<ENamedElement>)(EList<?>)EcoreFactory.eINSTANCE.createEAnnotation().getContents();
      for (int i = 0; i < count; ++i)
      {
        switch (random.nextInt(10))
        {
          case 0:
          {
            list.add(EcoreFactory.eINSTANCE.createEAttribute());
            break;
          }
          case 1:
          {
            list.add(EcoreFactory.eINSTANCE.createEClass());
            break;
          }
          case 2:
          {
            list.add(EcoreFactory.eINSTANCE.createEDataType());
            break;
          }
          case 3:
          {
            list.add(EcoreFactory.eINSTANCE.createEEnum());
            break;
          }
          case 4:
          {
            list.add(EcoreFactory.eINSTANCE.createEEnumLiteral());
            break;
          }
          case 5:
          {
            list.add(EcoreFactory.eINSTANCE.createEOperation());
            break;
          }
          case 6:
          {
            list.add(EcoreFactory.eINSTANCE.createEPackage());
            break;
          }
          case 7:
          {
            list.add(EcoreFactory.eINSTANCE.createEParameter());
            break;
          }
          case 8:
          {
            list.add(EcoreFactory.eINSTANCE.createEReference());
            break;
          }
          case 9:
          {
            list.add(EcoreFactory.eINSTANCE.createETypeParameter());
            break;
          }
        }
        (lastENamedElement = list.get(i)).setName("_" + random.nextInt());
      }
    }

    @Override
    public int run()
    {
      EObjectContainmentEList<ENamedElement> eList = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (eList.contains(lastENamedElement) ? 1 : 0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + (eList.contains(lastENamedElement) ? 1 : 0); // Containment list";
    }
  }

  public static class ENamedElementHashSetContains extends Benchmark
  {
    protected HashSet<ENamedElement> set;
    protected ENamedElement lastENamedElement;
    protected Random random = new Random(0);

    public ENamedElementHashSetContains()
    {
      super(1000);
      set = new HashSet<ENamedElement>();
      for (int i = 0; i < count; ++i)
      {
        switch (random.nextInt(10))
        {
          case 0:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEAttribute());
            break;
          }
          case 1:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEClass());
            break;
          }
          case 2:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEDataType());
            break;
          }
          case 3:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEEnum());
            break;
          }
          case 4:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEEnumLiteral());
            break;
          }
          case 5:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEOperation());
            break;
          }
          case 6:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEPackage());
            break;
          }
          case 7:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEParameter());
            break;
          }
          case 8:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEReference());
            break;
          }
          case 9:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createETypeParameter());
            break;
          }
        }
        lastENamedElement.setName("_" + random.nextInt());
      }
    }

    @Override
    public int run()
    {
      HashSet<ENamedElement> set = this.set;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (set.contains(lastENamedElement) ? 1 : 0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return " total += i + (set.contains(lastENamedElement) ? 1 : 0);";
    }
  }

  public static class ENamedElementSynchronizedWrapperHashSetContains extends Benchmark
  {
    protected Set<ENamedElement> set;
    protected ENamedElement lastENamedElement;
    protected Random random = new Random(0);

    public ENamedElementSynchronizedWrapperHashSetContains()
    {
      super(1000);
      set = Collections.synchronizedSet(new HashSet<ENamedElement>());
      for (int i = 0; i < count; ++i)
      {
        switch (random.nextInt(10))
        {
          case 0:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEAttribute());
            break;
          }
          case 1:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEClass());
            break;
          }
          case 2:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEDataType());
            break;
          }
          case 3:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEEnum());
            break;
          }
          case 4:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEEnumLiteral());
            break;
          }
          case 5:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEOperation());
            break;
          }
          case 6:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEPackage());
            break;
          }
          case 7:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEParameter());
            break;
          }
          case 8:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEReference());
            break;
          }
          case 9:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createETypeParameter());
            break;
          }
        }
        lastENamedElement.setName("_" + random.nextInt());
      }
    }

    @Override
    public int run()
    {
      Set<ENamedElement> synchronizedSet = this.set;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (synchronizedSet.contains(lastENamedElement) ? 1 : 0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return " total += i + (set.contains(lastENamedElement) ? 1 : 0); // Wrapped";
    }
  }

  public static class ENamedElementSynchronizedOverrideHashSetContains extends Benchmark
  {
    protected Set<ENamedElement> set;
    protected ENamedElement lastENamedElement;
    protected Random random = new Random(0);

    private static class MyHashSet<E> extends HashSet<E>
    {
      private static final long serialVersionUID = 1L;

      @Override
      public synchronized boolean contains(Object o)
      {
        return super.contains(o);
      }
    }

    public ENamedElementSynchronizedOverrideHashSetContains()
    {
      super(1000);
      set = new MyHashSet<ENamedElement>();
      for (int i = 0; i < count; ++i)
      {
        switch (random.nextInt(10))
        {
          case 0:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEAttribute());
            break;
          }
          case 1:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEClass());
            break;
          }
          case 2:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEDataType());
            break;
          }
          case 3:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEEnum());
            break;
          }
          case 4:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEEnumLiteral());
            break;
          }
          case 5:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEOperation());
            break;
          }
          case 6:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEPackage());
            break;
          }
          case 7:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEParameter());
            break;
          }
          case 8:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createEReference());
            break;
          }
          case 9:
          {
            set.add(lastENamedElement = EcoreFactory.eINSTANCE.createETypeParameter());
            break;
          }
        }
        lastENamedElement.setName("_" + random.nextInt());
      }
    }

    @Override
    public int run()
    {
      Set<ENamedElement> synchronizedSet = this.set;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (synchronizedSet.contains(lastENamedElement) ? 1 : 0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return " total += i + (set.contains(lastENamedElement) ? 1 : 0); // Override";
    }
  }

  public static class StringArrayListContains extends Benchmark
  {
    protected ArrayList<String> list;
    protected String lastString;
    protected Random random = new Random(0);

    public StringArrayListContains()
    {
      super(1000);
      list = new ArrayList<String>(count);
      for (int i = 0; i < count; ++i)
      {
        list.add(lastString = new String(String.valueOf(random.nextInt())));
      }
    }

    @Override
    public int run()
    {
      ArrayList<String> list = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (list.contains(lastString) ? 0 : 1);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + (list.contains(lastString) ? 0 : 1)";
    }
  }

  public static class StringFastEListContains extends Benchmark
  {
    protected BasicEList.FastCompare<String> list;
    protected String lastString;
    protected Random random = new Random(0);

    public StringFastEListContains()
    {
      super(1000);
      list = new BasicEList.FastCompare<String>(count);
      for (int i = 0; i < count; ++i)
      {
        list.add(lastString = new String(String.valueOf(random.nextInt())));
      }
    }

    @Override
    public int run()
    {
      BasicEList.FastCompare<String> list = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (list.contains(lastString) ? 0 : 1);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + (list.contains(lastString) ? 0 : 1)";
    }
  }

  public static class ObjectAllocation extends Benchmark
  {
    protected Object[] objects;

    public ObjectAllocation()
    {
      super(10000);
      objects = new Object[count];
    }

    @Override
    public int run()
    {
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (objects[i] = new Object()).hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + (objects[i] = new Object()).hashCode();";
    }
  }

  public static class ObjectAllocation2 extends Benchmark
  {
    public ObjectAllocation2()
    {
      super(1000000);
    }

    @Override
    public int run()
    {
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + new Object().hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + new Object().hashCode();";
    }
  }

  protected static class URICreation extends Benchmark
  {
    protected long counter;
    protected int repetition;

    protected String[][] strings;
    protected URI[][] uris;

    public URICreation()
    {
      super(10000);
      reset();
    }

    @Override
    public int run()
    {
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (uris[repetition][i] = URI.createURI(strings[repetition][i])).hashCode();
      }
      ++repetition;
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + (uris[repetition][i] = URI.createURI(strings[repetition][i])).hashCode();";
    }

    @Override
    public void reset()
    {
      repetition = 0;

      System.out.println("Reseting");

      uris = new URI[repeat][];
      strings = new String[repeat][];

      System.gc();
      System.gc();

      for (int i = 0; i < repeat; ++i)
      {
        uris[i] = new URI[count];
        strings[i] = new String[count];
        for (int j = 0; j < count; ++j)
        {
          strings[i][j] = "platform:/resource/project/path/file/" + counter++;
        }
      }

      System.gc();
      System.gc();

      try
      {
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
        // Let any daemons work for a while.
      }

      System.out.println("Finished reseting");
      System.out.flush();
    }
  }

  protected static class URI2Creation extends Benchmark
  {
    protected long counter;
    protected int repetition;

    protected String[][] strings;
    protected URITest.URI2[][] uris;

    public URI2Creation()
    {
      super(10000);
      reset();
    }

    @Override
    public int run()
    {
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (uris[repetition][i] = URITest.URI2.createURI(strings[repetition][i])).hashCode();
      }
      ++repetition;
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + (uris[repetition][i] = URITest.URI2.createURI(strings[repetition][i])).hashCode();";
    }

    @Override
    public void reset()
    {
      repetition = 0;

      System.out.println("Reseting");

      uris = new URITest.URI2[repeat][];
      strings = new String[repeat][];

      System.gc();
      System.gc();

      for (int i = 0; i < repeat; ++i)
      {
        uris[i] = new URITest.URI2[count];
        strings[i] = new String[count];
        for (int j = 0; j < count; ++j)
        {
          strings[i][j] = "platform:/resource/project/path/file/" + counter++;
        }
      }

      System.gc();
      System.gc();

      try
      {
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
        // Let any daemons work for a while.
      }

      System.out.println("Finished reseting");
      System.out.flush();
    }
  }

  public static class URIComparison extends Benchmark
  {
    protected URI uri1 = URI.createURI("platform:/resource/project/path/file/").appendSegment("0");
    protected URI uri2 = URI.createURI("platform:/resource/project/path/file/").appendSegment("1");
    protected URI uri3 = URI.createURI("platform:/resource/project/path/file/").appendSegment("2");
    protected URI uri4 = URI.createURI("platform:/resource/project/path/file/").appendSegment("3");
    protected Object[] choose = { uri1, uri2, uri3, uri4 };

    public URIComparison()
    {
      super(100000);
    }

    @Override
    public int run()
    {
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (uri1.equals(choose[i & 3]) ? 1 : 0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "compare1";
    }
  }

  public static class URI2Comparison extends Benchmark
  {
    protected URITest.URI2 uri1 = URITest.URI2.createURI("platform:/resource/project/path/file/").appendSegment("0");
    protected URITest.URI2 uri2 = URITest.URI2.createURI("platform:/resource/project/path/file/").appendSegment("1");
    protected URITest.URI2 uri3 = URITest.URI2.createURI("platform:/resource/project/path/file/").appendSegment("2");
    protected URITest.URI2 uri4 = URITest.URI2.createURI("platform:/resource/project/path/file/").appendSegment("3");
    protected Object[] choose = { uri1, uri2, uri3, uri4 };

    public URI2Comparison()
    {
      super(100000);
    }

    @Override
    public int run()
    {
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + (uri1.equals(choose[i & 3]) ? 1 : 0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "compare2";
    }
  }

  public static class HashMapGet extends Benchmark
  {
    protected HashMap<Object, String> map = new HashMap<Object, String>();
    protected Object[] choose = { new Object(), new Object(), new Object(), new Object() };

    public HashMapGet()
    {
      super(100000);
      for (Object key : choose)
      {
        map.put(key, "x");
      }
    }

    @Override
    public int run()
    {
      Map<Object, String> map = this.map;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + map.get(choose[i & 3]).hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + map.get(choose[i & 3]).hashCode();";
    }
  }

  public static class EObjectEGet extends Benchmark
  {
    protected EObject eObject = EcorePackage.eINSTANCE;
    protected EStructuralFeature[] choose = { EcorePackage.Literals.EPACKAGE__NS_PREFIX, EcorePackage.Literals.ENAMED_ELEMENT__NAME, EcorePackage.Literals.EPACKAGE__NS_PREFIX, EcorePackage.Literals.EPACKAGE__NS_URI };

    public EObjectEGet()
    {
      super(100000);
    }

    @Override
    public int run()
    {
      EObject eObject = this.eObject;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        total += i + eObject.eGet(choose[i & 3]).hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += i + eObject.eGet(choose[i & 3]).hashCode();";
    }
  }

  public static class ReflectiveAccess extends Benchmark
  {
    protected Object object = EcorePackage.eINSTANCE;
    protected Field[] choose;

    public ReflectiveAccess()
    {
      super(100000);
      try
      {
        Class<?> packageClass = EPackageImpl.class;
        Class<?> namedElementClass = ENamedElementImpl.class;

        choose =
          new Field[]
          {
            packageClass.getDeclaredField("nsPrefix"),
            namedElementClass.getDeclaredField("name"),
            packageClass.getDeclaredField("nsPrefix"),
            packageClass.getDeclaredField("nsURI"),
          };

        for (Field field : choose)
        {
          field.setAccessible(true);
        }
      }
      catch (Throwable throwable)
      {
        throw new RuntimeException(throwable);
      }
    }

    @Override
    public int run()
    {
      try
      {
        Object object = this.object;
        int total = 0;
        for (int i = 0, count = this.count; i < count; ++i)
        {
          total += i + choose[i & 3].get(object).hashCode();
        }
        return total;
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
    }

    @Override
    public String getLogic()
    {
      return "total += i + choose[i & 3].get(object).hashCode();";
    }
  }

  public static class LoopCounterEmpty extends Benchmark
  {
    protected int size ;
    protected List<Object> list;

    public LoopCounterEmpty(int size)
    {
      super(100000);
      list = new ArrayList<Object>(size);
      this.size = size;
      for (int i = 0; i < size; ++i)
      {
        list.add(new Object());
      }
    }

    @Override
    public int run()
    {
      List<Object> list = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        for (int j = 0, size = list.size(); j < size; ++j)
        {
          total += i + list.get(j).hashCode();
        }
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "for (int j = 0, size = list.size(); j < size; ++j)\n{\n  total += i + list.get(j).hashCode();\n} // " + size;
    }
  }

  public static class LoopForEachEmpty extends Benchmark
  {
    protected int size ;
    protected List<Object> list;

    public LoopForEachEmpty(int size)
    {
      super(100000);
      list = new ArrayList<Object>(size);
      this.size = size;
      for (int i = 0; i < size; ++i)
      {
        list.add(new Object());
      }
    }

    @Override
    public int run()
    {
      List<Object> list = this.list;
      int total = 0;
      for (int i = 0, count = this.count; i < count; ++i)
      {
        for (Object object : list)
        {
          total += i + object.hashCode();
        }
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "for (Object object : list)\n{\n  total += i + object.hashCode();\n} // " + size;
    }
  }

  public static void main(String[] args)
  {
    Benchmark[] benchmarks =
      {
        // new CountedLoop(),
        // new CountedLoopWithLocalCounter(),
        // new CountedLoopWithAdd(),
        // new ArrayReadAtIndex(),
        // new ArrayReadAtIndexAndAddIndex(),
        // new ArrayReadAtIndexAndAddIndexWithArrayLengthGuard(),
        // new ArrayReadAtIndexAddTwice(),
        // new ArrayUpdateIndex(),
        // new ObjectArrayHashCode(),
        // new StringArrayHashCode(),
        // new StringArrayListHashCode(),
        // new StringArrayListHashCodeWithSizeCall(),
        // new StringArrayListHashCodeWithForEachLoop(),
        // new StringBasicEListHashCode(),
        // new StringBasicEListHashCodeWithForEachLoop(),
        // new ENamedElementArrayGetName(),
        // new ENamedElementArrayListGetName(),
        // new StringArrayCharAt(),
        //new StringArrayListCharAt(),
        // new EModelElementArrayListGetName(),
        // new ENamedElementSynchronizedWrapperHashSetContains(),
        // new ENamedElementSynchronizedOverrideHashSetContains(),
        // new ENamedElementHashSetContains(),
        // new ENamedElementContainmentEListContains(),
        // new ENamedElementArrayListContains(),
        // new ENamedElementFastEListContains(),
        // new StringArrayListContains(),
        // new StringFastEListContains(),
        // new ObjectAllocation2(),
        // new URICreation(),
        // new URI2Creation(),
        // new URI2Comparison(),
        // new URIComparison(),
        // new HashMapGet(),
        // new EObjectEGet(),
        // new ReflectiveAccess(),
        // new LoopCounterEmpty(0),
        // new LoopForEachEmpty(0),
        // new LoopCounterEmpty(10),
        // new LoopForEachEmpty(10),
      };
    new BenchmarkHarness(1).run(20, benchmarks);
  }
}
