package sample;

/**
 * Created by Marina on 11.05.2016.
 */
public class WaveletTransform {


    public Double[] fastWaveletTransform(Double[] x) {
        int length = x.length;

        if (length == 1)
            return x;

        Double[] high = new Double[length / 2];
        Double[] low = new Double[length / 2];
        for (int i = 0; i < length / 2; i++) {
            high[i] = (x[i * 2] - x[i * 2 + 1]) / 2;
            low[i] = (x[i * 2] + x[i * 2 + 1]) / 2;
        }

        Double[] subLow = fastWaveletTransform(low);

        Double[] result = new Double[length];
        for(int i = 0; i < length / 2; i++) {
            result[i] =  high[i];
            result[i + length / 2] = subLow[i];
        }
        return result;
    }

    public Double[] reverseFastWaveletTransform(Double[] x) {
        int length = x.length;

        if (length ==  1)
            return x;

        Double[] subX = new Double[length / 2];
        for(int i = 0; i < length / 2; i++) {
            subX[i] = x[i + length / 2];
        }
        Double[] temp = reverseFastWaveletTransform(subX);

        Double[] result = new Double[length];
        for(int i = 0; i < length / 2; i++) {
            int j = i * 2;
            result[j] = temp[i] + x[i];
            result[j + 1] = temp[i] - x[i];
        }
        return result;

    }


}
