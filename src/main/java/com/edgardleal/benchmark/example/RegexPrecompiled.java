package com.edgardleal.benchmark.example;

import com.edgardleal.benchmark.Benchmark;

import java.util.regex.Pattern;

/**
 * Created by edgardleal on 25/07/16.
 */
class RegexPrecompiled  implements Runnable {
  Pattern pattern = Pattern.compile(".*([\\d]+)");
  public static final String TEXT = "lkjaslkdjflkajsdlfjalsjdlfkjalksjdlfkjlkjlk23423423";

  @Override
  public void run() {
    for(int i = 0; i< Benchmark.ITERATIONS; i++) {
      pattern.matcher(TEXT).matches();
    }
  }
}
