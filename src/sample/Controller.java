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
    Integer signal[] = {6, 10, 7, 2, 10, 12, 8, 6, 1, 6, 4, 13, 11, 8, 12, 13};

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
        Integer[] transformedSignal = transform.fastWaveletTransform(signal);
        for(Integer i = 0; i < N; i++){
            seriesAbsolute.getData().add(new XYChart.Data<>(i.toString(), transformedSignal[i]));
        }
        convert.getData().add(seriesAbsolute);

        reverse.getXAxis().setAutoRanging(true);
        reverse.getYAxis().setAutoRanging(true);
        XYChart.Series seriesReverse = new XYChart.Series<>();
        seriesReverse.setName("Restored Signal");
        Integer[] restoredSignal = transform.reverseFastWaveletTransform(transformedSignal);
        for(Integer i = 0; i < N; i++){
            seriesReverse.getData().add(new XYChart.Data<>(i.toString(), signal[i]));
        }
        reverse.getData().add(seriesReverse);
    }


}
