package com.edgardleal.benchmark;

/**
 * Created by edgardleal on 25/07/16.
 *
 * @author edgardleal
 * @version $Id: $Id
 */
public class Result {
  private String category;
  private long duration;
  private long start;
  private long memory;

  /**
   * <p>Constructor for Result.</p>
   *
   * @param startTime a long.
   */
  public Result(long startTime) {
    this.duration = System.nanoTime() - startTime;
    this.memory = Runtime.getRuntime().freeMemory();
  }

  public Result() {

  }

  /**
   * <p>Getter for the field <code>category</code>.</p>
   *
   * @return a {@link java.lang.String} object.
   */
  public String getCategory() {
    return category;
  }

  /**
   * <p>Setter for the field <code>category</code>.</p>
   *
   * @param category a {@link java.lang.String} object.
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    return String.format("| %s | %s |", formatter.time(this.duration), formatter.memory(this.memory));
  }

  /**
   * <p>Getter for the field <code>duration</code>.</p>
   *
   * @return a long.
   */
  public long getDuration() {
    return duration;
  }

  /**
   * <p>Setter for the field <code>duration</code>.</p>
   *
   * @param duration a long.
   */
  public void setDuration(long duration) {
    this.duration = duration;
  }

  /**
   * <p>Getter for the field <code>memory</code>.</p>
   *
   * @return a long.
   */
  public long getMemory() {
    return memory;
  }

  /**
   * <p>Setter for the field <code>memory</code>.</p>
   *
   * @param memory a long.
   */
  public void setMemory(long memory) {
    this.memory = memory;
  }

  public void start() {
    this.start = System.nanoTime();
  }

  public void stop() {
    this.duration = System.nanoTime() - this.start;
  }
}
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
