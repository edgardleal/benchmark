package com.edgardleal.benchmark.chart;

import com.edgardleal.benchmark.Benchmark;
import com.edgardleal.benchmark.Result;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;

/**
 * Created by edgardleal on 25/07/16.
 */
public class Render {
  private static final int ONE_SECOND = 1000;
  private int chartWidth  = 700;
  private int chartHeight = 400;

  /**
   * Render a chart file for many Benchmarks.
   * @param benchmarks
   * @param file
   * @throws IOException
   */
  public void generateChartToFile(Benchmark[] benchmarks, final String file) throws IOException {
    XYSeriesCollection categoryDataset = new XYSeriesCollection();
    for (Benchmark benchmark : benchmarks) {
      int counter = 0;
      XYSeries xySeries = new XYSeries(benchmark.getName());
      for (Result result : benchmark.getResults()) {
        xySeries.add(counter++, result.getDuration() / 1000_000);
      }
      categoryDataset.addSeries(xySeries);
    }

    JFreeChart freeChart =
        ChartFactory.createXYLineChart(file,
            "Executions", "Time ms",
            categoryDataset,
            PlotOrientation.VERTICAL,
            true, true, false);

    ChartUtilities.saveChartAsPNG(new File(file), freeChart, chartWidth, chartHeight);
  }

  /**
   * Render a chart file for result of one Benchmark execution.
   * @param results
   * @param file
   * @throws IOException
   */
  public void generateChartToFile(Result[] results, final String file) throws IOException {
    DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
    int counter = 0;
    for (Result result : results) {
      categoryDataset.addValue(result.getDuration() / ONE_SECOND, "Time", 
          String.valueOf(counter++));
      // TODO: check if values of time and memory are compatible when printed
      // categoryDataset.addValue(result.getMemory(), "Memory", String.valueOf(i));
    }

    JFreeChart freeChart =
        ChartFactory.createLineChart(file,
            "i", "Time",
            categoryDataset,
            PlotOrientation.VERTICAL,
            true, true, false);

    ChartUtilities.saveChartAsPNG(new File(file), freeChart, 700, 400);
  }
}
// vi: expandtab smarttab shiftwidth=2 tabstop=2 lbr tw=100
