package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Controller {
    @FXML
    public LineChart origin;

    @FXML
    public LineChart reverse;

    @FXML
    public LineChart convert;


    @FXML
    public Label fast;

    final int N = 16;
    Double signal[] = {6.0, 10.0, 7.0, 2.0, 10.0, 12.0, 8.0, 6.0, 1.0, 6.0, 4.0, 13.0, 11.0, 8.0, 12.0, 13.0};

    public  void initialize(){
        origin.getXAxis().setAutoRanging(true);
        origin.getYAxis().setAutoRanging(true);
        XYChart.Series seriesOrigin = new XYChart.Series<>();
        seriesOrigin.setName("Original Signal");
        for(Integer i = 0; i < N; i++){
            seriesOrigin.getData().add(new XYChart.Data<>(i.toString(), signal[i]));
        }
        origin.getData().add(seriesOrigin);

        convert.getXAxis().setAutoRanging(true);
        convert.getYAxis().setAutoRanging(true);
        XYChart.Series seriesAbsolute = new XYChart.Series<>();
        seriesAbsolute.setName("Transformed Signal");
        WaveletTransform transform = new WaveletTransform();
        Double[] transformedSignal = transform.fastWaveletTransform(signal);
        for(Integer i = 0; i < N; i++){
            seriesAbsolute.getData().add(new XYChart.Data<>(i.toString(), transformedSignal[i]));
        }
        convert.getData().add(seriesAbsolute);

        reverse.getXAxis().setAutoRanging(true);
        reverse.getYAxis().setAutoRanging(true);
        XYChart.Series seriesReverse = new XYChart.Series<>();
        seriesReverse.setName("Restored Signal");
        Double[] restoredSignal = transform.reverseFastWaveletTransform(transformedSignal);
        for(Integer i = 0; i < N; i++){
            seriesReverse.getData().add(new XYChart.Data<>(i.toString(), restoredSignal[i]));
        }
        reverse.getData().add(seriesReverse);
    }


}
