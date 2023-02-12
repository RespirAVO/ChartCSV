package alessandrosalerno.chartcsv.application;

import alessandrosalerno.chartcsv.application.uielements.ScrollableTable;
import alessandrosalerno.chartcsv.tools.data.Measurement;
import alessandrosalerno.chartcsv.tools.data.SingleMeasurementFactory;
import alessandrosalerno.chartcsv.tools.filesystem.CSVMeasurementReader;
import alessandrosalerno.chartcsv.tools.filesystem.GenericReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

public class ChartCSV extends ChartCSVApplication {
    private JMenuBar topMenuBar;
    private JSplitPane splitPanel;
    private ScrollableTable dataTable;
    private JPanel viewPanel;
    private ChartPanel chartPanel;
    private Measurement[] measurements;

    @Override
    public void buildUI() {
        this.buildMenus();
        this.buildMainUI();

        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
    }

    public void buildMenus() {
        this.topMenuBar = new JMenuBar();
        this.buildFileMenu();

        this.topMenuBar.setVisible(true);
        this.setJMenuBar(this.topMenuBar);
    }

    public void buildFileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.getAccessibleContext().setAccessibleDescription("File menu");

        JMenuItem open = new JMenuItem("Open");

        JMenu measurementsMenu = new JMenu("Measurements");
        measurementsMenu.setMnemonic(KeyEvent.VK_M);
        measurementsMenu.getAccessibleContext().setAccessibleDescription("Measurements menu");

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(ChartCSV.this, "Ok");

                GenericReader reader = new GenericReader(new CSVMeasurementReader(fileChooser.getSelectedFile().getAbsolutePath()));
                reader.readAll();
                ChartCSV.this.measurements = reader.getMeasurements();
                ChartCSV.this.topMenuBar.add(measurementsMenu);
                ChartCSV.this.topMenuBar.updateUI();
            }
        });

        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem no2 = new JRadioButtonMenuItem("NO2");
        group.add(no2);
        measurementsMenu.add(no2);

        JRadioButtonMenuItem voc = new JRadioButtonMenuItem("VOC");
        group.add(voc);
        measurementsMenu.add(voc);

        JRadioButtonMenuItem pm10 = new JRadioButtonMenuItem("PM10");
        group.add(pm10);
        measurementsMenu.add(pm10);

        JRadioButtonMenuItem pm25 = new JRadioButtonMenuItem("PM2.5");
        group.add(pm25);
        measurementsMenu.add(pm25);

        ActionListener as = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartCSV.this.fillData(((JRadioButtonMenuItem) e.getSource()).getText());
            }
        };

        no2.addActionListener(as);
        voc.addActionListener(as);
        pm10.addActionListener(as);
        pm25.addActionListener(as);

        fileMenu.add(open);
        this.topMenuBar.add(fileMenu);
    }

    public void buildMainUI() {
        this.splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        this.buildDataTable();
        this.buildViewPanel();

        this.splitPanel.setVisible(true);
        this.add(this.splitPanel, BorderLayout.CENTER);
    }

    public void buildDataTable() {
        this.dataTable = new ScrollableTable();
        this.splitPanel.add(this.dataTable);
    }

    public void buildViewPanel() {
        this.viewPanel = new JPanel();
        this.viewPanel.setVisible(true);
        this.splitPanel.add(viewPanel);
    }

    public void fillData(String measurement) {
        this.dataTable.empty();
        this.dataTable.addColumn("Day");
        this.dataTable.addColumn("Time");
        this.dataTable.addColumn(measurement);

        if (this.chartPanel != null) this.viewPanel.remove(this.chartPanel);
        XYSeries xySeries = new XYSeries(measurement);

        for (Measurement m : this.measurements) {
            this.dataTable.addRow(new Object[]{
                    new SimpleDateFormat("dd/MM/yyyy").format(m.date()),
                    m.date().getHours() + ":" + m.date().getMinutes() + ":" + m.date().getSeconds(),
                    SingleMeasurementFactory.convert(m, measurement)
            });

            xySeries.add(m.date().getTime() - this.measurements[0].date().getTime(), SingleMeasurementFactory.convert(m, measurement));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(xySeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
                measurement,
                "Time (ms)",
                measurement + " (ppb)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(measurement,
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        this.chartPanel = new ChartPanel(chart);
        this.chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.viewPanel.setLayout(new BoxLayout(this.viewPanel, BoxLayout.LINE_AXIS));
        this.chartPanel.setBackground(Color.white);
        this.viewPanel.add(this.chartPanel);
    }
}
