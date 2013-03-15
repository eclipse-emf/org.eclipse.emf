/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EcoreFactory;

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
  protected static final long MICRO_SECONDS_PER_SECOND = 1000000L;

  /**
   * The number of nanoseconds in a second.
   */
  protected static final long NANO_SECONDS_PER_SECOND = 1000000000L;

  /**
   * The number of picoseconds in a second.
   */
  protected static final long PICO_SECONDS_PER_SECOND = 1000000000000L;

  /**
   * The {@link #accumulateResults() accumulated result} of the {@link #results} at the end of the call to {@link #measure(Benchmark)}.
   */
  protected int accumulatedResult;

  /**
   * Creates an instance.
   * @see #run(Benchmark...)}.
   */
  public BenchmarkHarness()
  {
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

    // Compute the bounds of the 95% confidence interval.
    // I.e., the range within which 95% of the measurements lie.
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
    System.err.print(" CR 95%: ");
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
    // Determine the elapsed interval for the benchmark.
    //
    long start = System.nanoTime();
    int total = 0;
    for (int i = 0; i < 10; ++i)
    {
      for (int j = 0; j < benchmark.repeat; ++j)
      {
        total += benchmark.run();
      }
    }
    long end = System.nanoTime();
    long elapsedTime = end - start;
    
    // Determine how many times the benchmark needs to be run in order to take close to a full seconod.
    //
    double fraction= 10.0 * benchmark.repeat * NANO_SECONDS_PER_SECOND / elapsedTime;
    
    // Because we call this method recursively, check that the new count is at least 10% bigger than the repetions we're already using...
    //
    if (fraction > 1.1 * benchmark.repeat)
    {
      // Calibrate again with the new repeat count to ensure that this does achive a result close to one second.
      //
      benchmark.repeat = (int)fraction;
      total += calibrate(benchmark);
    }

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

    // Validate the benchmark has run long enough to make the inaccuracy in System.nanoTime() insignificant.
    //
    if (elapsedTime < NANO_SECONDS_PER_SECOND / 2)
    {
      double fraction = 1.0 * NANO_SECONDS_PER_SECOND / elapsedTime;
      throw new RuntimeException("Measurement interval for the benchmark is less that 1 second so increase the benchmark's count from " + benchmark.count + " to at least " + (long)fraction * benchmark.count);
    }

    // Accumulate the result into a globally visible value, again to ensure the JIT doesn't decide to stop running the benchmarks.
    //
    accumulatedResult += total;

    // Covert the time to pico seconds and divide by the benchmark's count and repeat to derive per-unit time.
    //
    return 1000L * elapsedTime / benchmark.count / repeat;
  }

  /**
   * Executes the benchmark for the specified number of repetitions and dumps the measurement statistics.
   */
  public void run(int repetitions, Benchmark benchmark)
  {
    // Accumulate meaningless result to circumvent aggressive JIT optimization of dead code.
    //
    accumulatedResult += calibrate(benchmark);

    // Each run is already an average of many benchmark runs.
    //
    long[] averages = new long[repetitions];
    for (int i = 0; i < repetitions; ++i)
    {
      averages[i] = measure(benchmark);
    }

    // Dump statistics about the averages of the averages.
    //
    summarize(true, benchmark, averages);
  }

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
     * for (int i = 0; i < count; ++i)
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
  }

  /**
   * Measure the performance of the given benchmarks,
   * dumping their statistics
   */
  public void run(int count, Benchmark... benchmarks)
  {
    for (int i = 0; i < 10; ++i)
    {
      for (int j = 0; j < benchmarks.length; ++j)
      {
        run(count, benchmarks[j]);
      }
    }

    System.out.println("!!" + accumulatedResult);
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
      for (int i = 0; i < count; ++i)
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
      for (int i = 0; i < count; ++i)
      {
        array[i] = i;
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "array[i] = i;";
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
      for (int i = 0; i < count; ++i)
      {
        total += array[i].hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += array[i].hashCode();";
    }
  }

  public static class StringArrayListHashCode extends Benchmark
  {
    protected List<String> list;
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
      List<String> list = this.list;
      int total = 0;
      for (int i = 0; i < count; ++i)
      {
        total += list.get(i).hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += list.get(i).hashCode();";
    }
  }

  public static class StringBasicEListHashCode extends Benchmark
  {
    protected List<String> list;
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
      List<String> eList = this.list;
      int total = 0;
      for (int i = 0; i < count; ++i)
      {
        total += eList.get(i).hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += eList.get(i).hashCode();";
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
      for (int i = 0; i < count; ++i)
      {
        total += array[i].charAt(0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += array[i].charAt(0);";
    }
  }

  public static class StringArrayListCharAt extends Benchmark
  {
    protected List<String> list;
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
      List<String> list = this.list;
      int total = 0;
      for (int i = 0; i < count; ++i)
      {
        total += list.get(i).charAt(0);
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += list.get(i).charAt(0);";
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
      for (int i = 0; i < count; ++i)
      {
        total += array[i].getName().hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += array[i].getName().hashCode();";
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
      for (int i = 0; i < count; ++i)
      {
        total += list.get(i).getName().hashCode();
      }
      return total;
    }

    @Override
    public String getLogic()
    {
      return "total += list.get(i).getName().hashCode();";
    }
  }

  public static void main(String[] args)
  {
    Benchmark[] benchmarks =
      {
        // new CountedLoop(),
        // new CountedLoopWithAdd(),
        // new ArrayReadAtIndex(),
        // new ArrayReadAtIndexAndAddIndex(),
        // new ArrayReadAtIndexAddTwice(),
        // new ArrayUpdateIndex(),
        // new StringArrayHashCode(),
        // new StringArrayListHashCode(),
        // new StringArrayListHashCodeWithForEachLoop(),
        // new StringBasicEListHashCode(),
        // new StringBasicEListHashCodeWithForEachLoop(),
        new ENamedElementArrayGetName(),
        new ENamedElementArrayListGetName(),
        // new StringArrayCharAt(),
        // new StringArrayListCharAt(),
      };
    new BenchmarkHarness().run(20, benchmarks);
  }
}
