import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        PiePlot3D piePlot3D = new PiePlot3D();
        piePlot3D.setDataset(dataset);
        dataset.setValue("Category 1", Double.valueOf("10"));
        dataset.setValue("Category 2", Double.valueOf("20"));
        dataset.setValue("Category 3", Double.valueOf("30"));
        dataset.setValue("Category 4", Double.valueOf("40"));
        JFreeChart jfreechart = ChartFactory.createPieChart("Category 1", dataset, true, true, true);
        ChartFrame chartframe = new ChartFrame("Category 1", jfreechart);
        piePlot3D.setDataset(dataset);
        chartframe.setVisible(true);

    }
}