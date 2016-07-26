package com.edgardleal.benchmark;

/**
 * Created by edgardleal on 25/07/16.
 */
public class Formatter {

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
