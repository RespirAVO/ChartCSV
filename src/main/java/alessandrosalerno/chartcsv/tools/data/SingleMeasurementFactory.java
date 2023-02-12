package alessandrosalerno.chartcsv.tools.data;

public class SingleMeasurementFactory {
    public static double convert(Measurement measurement, String singleMeasurement) {
        return switch (singleMeasurement) {
            case "NO2" -> measurement.no2();
            case "VOC" -> measurement.voc();
            case "PM10" -> measurement.pm10();
            case "PM2.5" -> measurement.pm25();
            default -> 0;
        };
    }
}
