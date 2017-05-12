package com.edgardleal.benchmark;

/**
 * Created by edgardleal on 25/07/16.
 *
 * @author edgardleal
 * @version $Id: $Id
 */
public class Formatter {

  /** Constant <code>ONE_K=1024L</code> */
  public static final long ONE_K = 1024L;
  /** Constant <code>ONE_M=1024L * ONE_K</code> */
  public static final long ONE_M = 1024L * ONE_K;
  /** Constant <code>ONE_G=1024L * ONE_M</code> */
  public static final long ONE_G = 1024L * ONE_M;
  /** Constant <code>ONE_MILISECOND=1000000D</code> */
  public static final double ONE_MILISECOND = 1000000D;
  /** Constant <code>ONE_SECOND=ONE_MILISECOND * 1000D</code> */
  public static final double ONE_SECOND = ONE_MILISECOND * 1000D;
  /** Constant <code>ONE_MINUTE=ONE_SECOND * 60D</code> */
  public static final double ONE_MINUTE = ONE_SECOND * 60D;

  /**
   * <p>time.</p>
   *
   * @param duration a double.
   * @return a {@link java.lang.String} object.
   */
  public String time(final double duration) {
    return time(duration, 3);
  }

  /**
   * <p>time.</p>
   *
   * @param duration a double.
   * @param length a int.
   * @return a {@link java.lang.String} object.
   */
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

  /**
   * <p>memory.</p>
   *
   * @param memory a long.
   * @return a {@link java.lang.String} object.
   */
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
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
