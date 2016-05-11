package sample;

/**
 * Created by Marina on 11.05.2016.
 */
public class WaveletTransform {


    public Integer[] fastWaveletTransform(Integer[] x) {
        int length = x.length;

        if (length == 1)
            return x;

        Integer[] high = new Integer[length / 2];
        Integer[] low = new Integer[length / 2];
        for (int i = 0; i < length / 2; i++) {
            high[i] = (x[i * 2] - x[i * 2 + 1]) / 2;
            low[i] = (x[i * 2] + x[i * 2 + 1]) / 2;
        }

        Integer[] subLow = fastWaveletTransform(low);

        Integer[] result = new Integer[length];
        for(int i = 0; i < length / 2; i++) {
            result[i] =  high[i];
            result[i + length / 2] = subLow[i];
        }
        return result;
    }

    public Integer[] reverseFastWaveletTransform(Integer[] x) {
        int length = x.length;

        if (length ==  1)
            return x;

        Integer[] subX = new Integer[length / 2];
        for(int i = 0; i < length / 2; i++) {
            subX[i] = x[i + length / 2];
        }
        Integer[] temp = reverseFastWaveletTransform(subX);

        Integer[] result = new Integer[length];
        for(int i = 0; i < length / 2; i++) {
            int j = i * 2;
            result[j] = temp[i] - x[i];
            result[j + 1] = temp[i] + x[i];
        }
        return result;

    }


}
