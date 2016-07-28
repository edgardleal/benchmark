package com.edgardleal.benchmark;

/**
 * Created by edgardleal on 25/07/16.
 */
public class Result {
  private String category;
  private long duration;
  private long memory;

  public Result(long startTime, long startMemory) {
    this.duration = System.nanoTime() - startTime;
    this.memory = Runtime.getRuntime().freeMemory();
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public String toString() {
    Formatter formatter = new Formatter();
    return String.format("| %s | %s |", formatter.time(this.duration), formatter.memory(this.memory));
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public long getMemory() {
    return memory;
  }

  public void setMemory(long memory) {
    this.memory = memory;
  }
}
