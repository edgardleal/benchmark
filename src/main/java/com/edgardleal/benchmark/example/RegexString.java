package com.edgardleal.benchmark.example;

import com.edgardleal.benchmark.Benchmark;

/**
 * Created by edgardleal on 25/07/16.
 */
class RegexString implements Runnable {

  /** Constant <code>TEXT="lkjaslkdjflkajsdlfjalsjdlfkjalksjdlfkjl"{trunked}</code> */
  public static final String TEXT = "lkjaslkdjflkajsdlfjalsjdlfkjalksjdlfkjlkjlk23423423";
  /** Constant <code>REGEX=".*([\\d]+)"</code> */
  public static final String REGEX = ".*([\\d]+)";

  /**
   * <p>Constructor for RegexString.</p>
   */
  public RegexString() {
  }

  /** {@inheritDoc} */
  @Override
  public void run() {
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      REGEX.matches(TEXT);
    }
  }
}
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
