package pl.elka.pttw;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class ConstellationChart extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConstellationChart(String applicationTitle, String chartTitle, XYDataset Dataset) {
		super(applicationTitle);
		JFreeChart xyChart = ChartFactory.createScatterPlot(chartTitle, "RE", "IM", Dataset,
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
			ChartUtilities.saveChartAsPNG(new File("result.png"), xyChart , 560, 367);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
