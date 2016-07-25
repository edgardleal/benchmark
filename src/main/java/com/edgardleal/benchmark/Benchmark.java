package com.edgardleal.benchmark;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * Created by edgardleal on 24/07/16.
 */
public class Benchmark {
  private static final long ONE_SECOND = 1000L;
  private static final long ONE_MINUTE = 60L * ONE_SECOND;
  private static final long MAX_WAIT = ONE_MINUTE;
  public static final int ITERATIONS = 10000;
  private final Runnable runnable;

  class Statistics {
    private SummaryStatistics timeStats;

    public Statistics(Result[] list) throws NoSuchFieldException, IllegalAccessException {
      this.timeStats = new SummaryStatistics();
      double total = 0D;
      for (Result obj : list) {
        Double value = Double.valueOf(obj.duration);
        timeStats.addValue(value);
      }
    }

    @Override
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      Formatter formatter = new Formatter();

      int length = 6;
      stringBuilder.append("+----------+-----------+-----------+-----------+").append('\n');
      stringBuilder.append("| Min      | AVG       | Max       | SD        |").append('\n');
      stringBuilder.append("+----------+-----------+-----------+-----------+").append('\n')
          .append(String.format("| %s | %s  | %s  | %s  |\n",
              formatter.time(timeStats.getMin(), length),
              formatter.time(timeStats.getMean(), length),
              formatter.time(timeStats.getMax(), length),
              formatter.time(timeStats.getStandardDeviation(), length)))
          .append("+----------+-----------+-----------+-----------+").append('\n');
      return stringBuilder.toString();
    }
  }

  class Formatter {
    public static final long ONE_K = 1024L;
    public static final long ONE_M = 1024L * ONE_K;
    public static final long ONE_G = 1024L * ONE_M;
    public static final double ONE_MILISECOND = 1000000D;
    public static final double ONE_SECOND = ONE_MILISECOND * 1000D;
    public static final double ONE_MINUTE = ONE_SECOND * 60D;

    public String time(final double duration) {
      return time(duration, 3);
    }

    public String time(final double duration, int length) {
      if (duration < ONE_MILISECOND) {
        return String.format("%" + length + "dns", Math.round(duration / ONE_MILISECOND));
      } else if (duration < ONE_SECOND) {
        return String.format("%" + length + "dms", Math.round(duration / ONE_MILISECOND));
      } else if (duration < ONE_MINUTE) {
        return String.format("%" + length + "dse", Math.round(duration / ONE_SECOND));
      } else {
        return String.format("%3dmi", Math.round(duration / ONE_MINUTE));
      }
    }

    public String memory(long memory) {
      if (memory < ONE_K) {
        return String.format("%3dB ", memory);
      } else if (memory < ONE_M) {
        return String.format("%3dKb", Math.round(memory / ONE_K));
      } else if (memory < ONE_G) {
        return String.format("%3dMb", Math.round(memory / ONE_M));
      } else {
        return String.format("%3dGb", Math.round(memory / ONE_G));
      }
    }


  }

  class Result {
    long duration;
    long memory;

    public Result(long startTime, long startMemory) {
      this.duration = System.nanoTime() - startTime;
      this.memory = Runtime.getRuntime().freeMemory();
    }

    @Override
    public String toString() {
      Formatter formatter = new Formatter();
      return String.format("| %s | %s |", formatter.time(this.duration), formatter.memory(this.memory));
    }
  }

  public static final int MAX_OPERATIONS = 10;
  private Thread threads[] = new Thread[MAX_OPERATIONS];
  private Result results[] = new Result[MAX_OPERATIONS];


  public Benchmark(Runnable runnable) {
    this.runnable = runnable;
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

  public static void main(String[] args) throws IllegalAccessException, InstantiationException {
    Class<?> clazz;
    if (args.length > 0) {
      switch (args[0]) {
        case "1":
          clazz = GetsetExample.class;
          break;
        case "2":
          clazz = ReflectionExample.class;
          break;
        default:
          clazz = BeanUtilsExample.class;
      }
    } else {
      clazz = BeanUtilsExample.class;
    }
    Benchmark benchmark = new Benchmark((Runnable) clazz.newInstance());
    benchmark.start(50);
    System.out.printf("+----------------------------------------------+\n| %-45s|\n", clazz.getSimpleName());
    benchmark.printStatistics();
    System.out.println("Done");
  }
}
