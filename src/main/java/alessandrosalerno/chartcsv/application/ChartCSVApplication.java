package alessandrosalerno.chartcsv.application;

import javax.swing.*;

public abstract class ChartCSVApplication extends JFrame
        implements Buildable {
    public ChartCSVApplication() {
        this.setTitle("ChartCSV");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.buildUI();
    }
}
