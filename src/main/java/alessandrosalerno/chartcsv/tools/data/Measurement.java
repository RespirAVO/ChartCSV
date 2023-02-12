package alessandrosalerno.chartcsv.tools.data;

import java.util.Date;

public record Measurement(Date date,
                          double no2,
                          double voc,
                          double pm10,
                          double pm25) {
    @Override
    public String toString() {
        return "Measurement{" +
                "date=" + date +
                ", no2=" + no2 +
                ", voc=" + voc +
                ", pm10=" + pm10 +
                ", pm25=" + pm25 +
                '}';
    }
}
