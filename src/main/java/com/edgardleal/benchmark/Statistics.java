package com.edgardleal.benchmark;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * Created by edgardleal on 25/07/16.
 */
public class Statistics {
  private SummaryStatistics timeStats;

  public Statistics(Result[] list) throws NoSuchFieldException, IllegalAccessException {
    this.timeStats = new SummaryStatistics();
    for (Result obj : list) {
      Double value = Double.valueOf(obj.getDuration());
      timeStats.addValue(value);
    }
  }

  public SummaryStatistics getTimeStats() {
    return this.timeStats;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    Formatter formatter = new Formatter();

    int length = 6;
    stringBuilder.append("+----------+-----------+-----------+-----------+").append('\n');
    stringBuilder.append("| Min      | AVG       | Max       | SD        |").append('\n');
    stringBuilder.append("+----------+-----------+-----------+-----------+").append('\n')
        .append(String.format("| %s | %s  | %s  | %s  |\n",
            formatter.time(timeStats.getMin(), length),
            formatter.time(timeStats.getMean(), length),
            formatter.time(timeStats.getMax(), length),
            formatter.time(timeStats.getStandardDeviation(), length)))
        .append("+----------+-----------+-----------+-----------+").append('\n');
    return stringBuilder.toString();
  }
}
