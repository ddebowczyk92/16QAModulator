package pl.elka.pttw;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import pl.elka.pttw.constellation.Constellation;

public class ConstellationChart extends ApplicationFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final Constellation constellation;

    public ConstellationChart(String applicationTitle, String chartTitle, Constellation constellation, List<String> data) {
        super(applicationTitle);
        this.constellation = constellation;
        XYDataset dataset = this.getXYDataset(data);
        JFreeChart xyChart = ChartFactory.createScatterPlot(chartTitle, "RE", "IM", dataset,
                PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(xyChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));

        final XYPlot plot = xyChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesLinesVisible(0, false);
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
        try {
            ChartUtilities.saveChartAsPNG(new File("result.png"), xyChart, 560, 367);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XYDataset getXYDataset(List<String> grayCodeData) {

        double ampl, phase, x, y;

        final XYSeries constellation = new XYSeries("constellation");
        for (int i = 0; i < grayCodeData.size(); i++) {
            ampl = (double) this.constellation.getAmplitudeAndPhase(grayCodeData.get(i)).getKey();
            phase = (double) this.constellation.getAmplitudeAndPhase(grayCodeData.get(i)).getValue();
            x = ampl * Math.cos(Math.toRadians(phase));
            y = ampl * Math.sin(Math.toRadians(phase));
            constellation.add(x, y);
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(constellation);
        return dataset;
    }


}
