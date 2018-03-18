package pl.politechnika.poll.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.image.BufferedImage;
import java.util.Map;

public class PollBarChart {

    public static BufferedImage getChartImageFromValuesMap(String title, String xAxisName, String yAxisName, Map<String, String> valuesMap) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        valuesMap.forEach((columnName, value) -> dataset.addValue(Double.parseDouble(value), "", columnName));

        JFreeChart chart = ChartFactory.createBarChart(title, xAxisName, yAxisName, dataset);
        return chart.createBufferedImage(400, 400, BufferedImage.TYPE_INT_RGB, null);
    }
}
