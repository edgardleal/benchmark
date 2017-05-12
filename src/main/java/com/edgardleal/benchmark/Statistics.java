package com.edgardleal.benchmark;

import java.util.List;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * Created by edgardleal on 25/07/16.
 *
 * @author edgardleal
 * @version $Id: $Id
 */
public class Statistics {
  private SummaryStatistics timeStats;

  /**
   * <p>Constructor for Statistics.</p>
   *
   * @param list an array of {@link com.edgardleal.benchmark.Result} objects.
   * @throws java.lang.NoSuchFieldException if any.
   * @throws java.lang.IllegalAccessException if any.
   */
  public Statistics(Result[] list) throws NoSuchFieldException, IllegalAccessException {
    this.timeStats = new SummaryStatistics();
    for (Result obj : list) {
      Double value = Double.valueOf(obj.getDuration());
      timeStats.addValue(value);
    }
  }

  public Statistics(List<Result> results) {
    this.timeStats = new SummaryStatistics();
    for (Result obj : results) {
      Double value = Double.valueOf(obj.getDuration());
      timeStats.addValue(value);
    }
  }

  /**
   * <p>Getter for the field <code>timeStats</code>.</p>
   *
   * @return a {@link org.apache.commons.math3.stat.descriptive.SummaryStatistics} object.
   */
  public SummaryStatistics getTimeStats() {
    return this.timeStats;
  }

  /** {@inheritDoc} */
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
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
