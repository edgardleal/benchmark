package com.edgardleal.benchmark;

import java.util.regex.Pattern;

/**
 * Created by edgardleal on 27/07/16.
 */
public class Test {

  Pattern pattern = Pattern.compile(".*([\\d]+)");
  public static final String TEXT = "lkjaslkdjflkajsdlfjalsjdlfkjalksjdlfkjlkjlk23423423";
  public static final String REGEX = ".*([\\d]+)";

  public void timeCompiled(){
    for(int i = 0; i< Benchmark.ITERATIONS; i++) {
      pattern.matcher(TEXT).matches();
    }
  }

  public void timeNotCompiled() {
    for(int i = 0; i< Benchmark.ITERATIONS; i++) {
      REGEX.matches(TEXT);
    }
  }

}
