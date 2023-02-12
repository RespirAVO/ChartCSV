package alessandrosalerno.chartcsv.tools.filesystem;

import alessandrosalerno.chartcsv.tools.data.Measurement;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVMeasurementReader implements MeasurementReader {
    private final String path;

    public CSVMeasurementReader(String path) {
        this.path = path;
    }

    @Override
    public Measurement[] read() {
        List<Measurement> output = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(this.path), ',')) {
            reader.readNext();
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:sss").parse(nextLine[1]);
                double no2 = Double.parseDouble(nextLine[2]);
                double voc = Double.parseDouble(nextLine[3]);
                double pm10 = Double.parseDouble(nextLine[4]);
                double pm25 = Double.parseDouble(nextLine[5]);

                output.add(new Measurement(date, no2, voc, pm10, pm25));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Measurement[] realOutput = new Measurement[output.size()];
        output.toArray(realOutput);
        return realOutput;
    }
}
