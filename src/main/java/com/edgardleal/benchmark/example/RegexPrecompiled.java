package com.edgardleal.benchmark.example;

import com.edgardleal.benchmark.Benchmark;

import java.util.regex.Pattern;

/**
 * Created by edgardleal on 25/07/16.
 */
class RegexPrecompiled implements Runnable {
  /** Constant <code>TEXT="lkjaslkdjflkajsdlfjalsjdlfkjalksjdlfkjl"{trunked}</code> */
  public static final String TEXT = "lkjaslkdjflkajsdlfjalsjdlfkjalksjdlfkjlkjlk23423423";
  Pattern pattern = Pattern.compile(".*([\\d]+)");

  /** {@inheritDoc} */
  @Override
  public void run() {
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      pattern.matcher(TEXT).matches();
    }
  }
}
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
