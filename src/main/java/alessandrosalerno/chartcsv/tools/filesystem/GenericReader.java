package alessandrosalerno.chartcsv.tools.filesystem;

import alessandrosalerno.chartcsv.tools.data.Measurement;

public class GenericReader {
    private MeasurementReader reader;
    private Measurement[] measurements;

    public GenericReader(MeasurementReader reader) {
        this.reader = reader;
    }

    public void readAll() {
        this.measurements = this.reader.read();
    }

    public Measurement getMeasurement(int i) {
        return this.measurements[i];
    }

    public Measurement[] getMeasurements() {
        return this.measurements;
    }
}
