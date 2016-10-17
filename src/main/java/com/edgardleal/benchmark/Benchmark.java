package com.edgardleal.benchmark;

import com.edgardleal.benchmark.chart.Render;
import com.edgardleal.benchmark.example.ReflectionExample;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by edgardleal on 24/07/16.
 *
 * @author edgardleal
 * @version $Id: $Id
 */
public class Benchmark {
  /** Constant <code>ITERATIONS=1000</code> */
  public static final int ITERATIONS = 1000;
  /** Constant <code>MAX_OPERATIONS=10</code> */
  public static final int MAX_OPERATIONS = 10;
  private static final long ONE_SECOND = 1000L;
  private static final long ONE_MINUTE = 60L * ONE_SECOND;
  private static final long MAX_WAIT = ONE_MINUTE;
  private final Runnable runnable;
  private final String name;
  private Thread threads[];
  private Result results[];


  /**
   * <p>Constructor for Benchmark.</p>
   *
   * @param runnable a {@link java.lang.Runnable} object.
   * @param name a {@link java.lang.String} object.
   */
  public Benchmark(Runnable runnable, String name) {
    this.runnable = runnable;
    this.name = name;
  }

  /**
   * <p>benchmarkForRunnable.</p>
   *
   * @param runnable a {@link java.lang.Runnable} object.
   * @param name a {@link java.lang.String} object.
   * @return a {@link com.edgardleal.benchmark.Benchmark} object.
   */
  public static Benchmark benchmarkForRunnable(Runnable runnable, String name) {
    Benchmark benchmark = new Benchmark(runnable, name);
    benchmark.start(150); // warmup
    benchmark.start(150);
    System.out.printf("+----------------------------------------------+\n| %-45s|\n", name);
    benchmark.printStatistics();
    return benchmark;
  }

  /**
   * <p>drawImageFile.</p>
   *
   * @param benchmark a {@link com.edgardleal.benchmark.Benchmark} object.
   * @param clazz a {@link java.lang.String} object.
   */
  public static void drawImageFile(Benchmark benchmark, String clazz) {
    try {
      Result[] results = benchmark.results;
      new Render().generateChartToFile(results, clazz + ".png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * <p>main.</p>
   *
   * @param args an array of {@link java.lang.String} objects.
   * @throws java.lang.IllegalAccessException if any.
   * @throws java.lang.InstantiationException if any.
   * @throws java.lang.ClassNotFoundException if any.
   * @throws java.io.IOException if any.
   */
  public static void main(String[] args) throws IllegalAccessException, 
         InstantiationException, ClassNotFoundException, IOException {
    final Class<?> clazz;
    if (args.length > 0) {
      clazz = Class.forName(args[0]);
    } else {
      clazz = ReflectionExample.class;
    }
    Benchmark benchmark;
    Object object = clazz.newInstance();
    if (object instanceof Runnable) {
      benchmark = benchmarkForRunnable((Runnable) object, clazz.getSimpleName());
      drawImageFile(benchmark, clazz.getSimpleName());
    } else {
      Method[] methods = clazz.getDeclaredMethods();
      Benchmark[] benchmarks = new Benchmark[methods.length];
      int counter = 0;
      for (Method method : methods) {
        if (!method.getName().startsWith("time")) {
          continue;
        }
        benchmarks[counter++] = benchmarkForRunnable(new MethodRunner(method, object), method.getName());
      }
      new Render().generateChartToFile(benchmarks, clazz.getSimpleName() + ".png");
    }
    System.out.println("Done");
  }

  /**
   * <p>Create Threads and results for this execution.</p>
   */
  private void setup(int iterations) {
    this.threads = new Thread[iterations];
    this.results = new Result[iterations];
    for (int i = 0; i < iterations; i++) {
      threads[i] = new Thread(this.runnable, String.format("Benchmark - %d", i));
    }
  }

  /**
   * <p>start the benchmark.</p>
   *
   * @param iterations a int.
   */
  public void start(int iterations) {
    this.setup(iterations);
    long start = 0L;
    long memory = Runtime.getRuntime().freeMemory();
    for (int i = 0; i < iterations; i++) {
      start = System.nanoTime();
      threads[i].start();
      try {
        threads[i].join(MAX_WAIT);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      this.results[i] = new Result(start, memory);
      System.gc();
    }
  }

  /**
   * <p>printResults.</p>
   */
  public void printResults() {
    for (Result result :
        this.results) {
      System.out.println(result);
    }
  }

  /**
   * <p>printStatistics.</p>
   */
  public void printStatistics() {
    try {
      Statistics statistics = new Statistics(this.results);
      System.out.println(statistics.toString());
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  /**
   * <p>Getter for the field <code>results</code>.</p>
   *
   * @return an array of {@link com.edgardleal.benchmark.Result} objects.
   */
  public Result[] getResults() {
    return results;
  }

  /**
   * <p>Getter for the field <code>name</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getName() {
    return name;
  }
}
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
