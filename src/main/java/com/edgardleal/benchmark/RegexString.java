package com.edgardleal.benchmark;

/**
 * Created by edgardleal on 25/07/16.
 */
class RegexString implements Runnable {

  public static final String TEXT = "lkjaslkdjflkajsdlfjalsjdlfkjalksjdlfkjlkjlk23423423";
  public static final String REGEX = ".*([\\d]+)";

  @Override
  public void run() {
    for(int i = 0; i< Benchmark.ITERATIONS; i++) {
      REGEX.matches(TEXT);
    }
  }
}
