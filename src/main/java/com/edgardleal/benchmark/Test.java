package com.edgardleal.benchmark;

import java.util.regex.Pattern;

/**
 * Created by edgardleal on 27/07/16.
 *
 * @author edgardleal
 * @version $Id: $Id
 */
public class Test {

  /** Constant <code>TEXT="lkjaslkdjflkajsdlfjalsjdlfkjalksjdlfkjl"{trunked}</code> */
  public static final String TEXT = "lkjaslkdjflkajsdlfjalsjdlfkjalksjdlfkjlkjlk23423423";
  /** Constant <code>REGEX=".*([\\d]+)"</code> */
  public static final String REGEX = ".*([\\d]+)";
  Pattern pattern = Pattern.compile(".*([\\d]+)");

  /**
   * <p>timeCompiled.</p>
   */
  public void timeCompiled() {
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      pattern.matcher(TEXT).matches();
    }
  }

  /**
   * <p>timeNotCompiled.</p>
   */
  public void timeNotCompiled() {
    for (int i = 0; i < Benchmark.ITERATIONS; i++) {
      REGEX.matches(TEXT);
    }
  }

}
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
