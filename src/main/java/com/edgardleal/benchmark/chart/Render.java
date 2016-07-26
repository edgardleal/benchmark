package com.edgardleal.benchmark.chart;

import com.edgardleal.benchmark.Result;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;

/**
 * Created by edgardleal on 25/07/16.
 */
public class Render {

  public void generateChartToFile(Result[] results, final String file) throws IOException {
    DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
    int i = 0;
    for (Result result: results) {
      categoryDataset.addValue(result.getDuration() / 1000, "Time", String.valueOf(i++));
      // TODO: check if values of time and memory are compatible when printed
      // categoryDataset.addValue(result.getMemory(), "Memory", String.valueOf(i));
    }

    JFreeChart jFreeChart =
     ChartFactory.createLineChart( file,
        "i","Time",
        categoryDataset,
        PlotOrientation.VERTICAL,
        true,true,false);

    ChartUtilities.saveChartAsPNG(new File(file), jFreeChart, 700,400);
  }
}
