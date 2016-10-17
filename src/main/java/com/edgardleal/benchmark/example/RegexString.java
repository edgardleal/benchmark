package com.edgardleal.benchmark.example;

import com.edgardleal.benchmark.Benchmark;

/**
 * Created by edgardleal on 25/07/16.
 */
class RegexString implements Runnable {

  public static final String TEXT = "lkjaslkdjflkajsdlfjalsjdlfkjalksjdlfkjlkjlk23423423";
  public static final String REGEX = ".*([\\d]+)";

  public RegexString() {
  }

  @Override
  public void run() {
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      REGEX.matches(TEXT);
    }
  }
}
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
