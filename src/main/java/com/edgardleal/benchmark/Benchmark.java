package com.edgardleal.benchmark;

import com.edgardleal.benchmark.chart.Render;
import com.edgardleal.benchmark.example.ReflectionExample;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by edgardleal on 24/07/16.
 */
public class Benchmark {
  private static final long ONE_SECOND = 1000L;
  private static final long ONE_MINUTE = 60L * ONE_SECOND;
  private static final long MAX_WAIT = ONE_MINUTE;
  public static final int ITERATIONS = 10000;
  private final Runnable runnable;


  public static final int MAX_OPERATIONS = 10;
  private final String name;
  private Thread threads[] = new Thread[MAX_OPERATIONS];
  private Result results[] = new Result[MAX_OPERATIONS];


  public Benchmark(Runnable runnable, String name) {
    this.runnable = runnable;
    this.name = name;
  }

  private void setup(int iterations) {
    this.threads = new Thread[iterations];
    this.results = new Result[iterations];
    for (int i = 0; i < iterations; i++) {
      threads[i] = new Thread(this.runnable, String.format("Benchmark - %d", i));
    }
  }

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

  public void printResults() {
    for (Result result :
        this.results) {
      System.out.println(result);
    }
  }

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

  public static Benchmark benchmarkForRunnable(Runnable runnable, String name ) {
    Benchmark benchmark = new Benchmark(runnable, name);
    benchmark.start(150);
    System.out.printf("+----------------------------------------------+\n| %-45s|\n", name);
    benchmark.printStatistics();
    return benchmark;
  }

  public static void drawImageFile(Benchmark benchmark, String clazz) {
    try {
      Result[] results = benchmark.results;
      new Render().generateChartToFile(results, clazz + ".png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
    Class<?> clazz;
    if (args.length > 0) {
      clazz = Class.forName(args[0]);
    } else {
      clazz = ReflectionExample.class;
    }
    Benchmark benchmark = null;
    Object object = clazz.newInstance();
    if (object instanceof Runnable) {
      benchmark = benchmarkForRunnable((Runnable) object, clazz.getSimpleName());
      drawImageFile(benchmark, clazz.getSimpleName());
    } else {
      Method[] methods = clazz.getDeclaredMethods();
      Benchmark[] benchmarks = new Benchmark[methods.length];
      int i = 0;
      for (Method method : methods) {
        if (!method.getName().startsWith("time")) {
          continue;
        }
        benchmarks[i++] = benchmarkForRunnable(new MethodRunner(method, object), clazz.getSimpleName() + "." + method.getName());
        drawImageFile(benchmarks[i - 1], clazz.getSimpleName() + "." + method.getName());
      }

    }
    System.out.println("Done");
  }
}
