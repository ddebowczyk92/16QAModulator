package pl.elka.pttw.constellation;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dominik on 03/06/2017.
 */
public abstract class Constellation {

    private static final Map<String, AbstractMap.SimpleEntry<Double, Double>> modulation = new HashMap<>();

    public AbstractMap.SimpleEntry getAmplitudeAndPhase(String grayCode) {
        return modulation.get(grayCode);
    }

    public Double getAmplitude(String grayCode) {
        return modulation.get(grayCode).getKey();
    }

    public Double getPhase(String grayCode) {
        return modulation.get(grayCode).getValue();
    }

    public static final class Constellation16QAM1 extends Constellation {

        public Constellation16QAM1() {
            modulation.put("0000", new AbstractMap.SimpleEntry(1.4142, 135.0000));
            modulation.put("0001", new AbstractMap.SimpleEntry(1.1180, 116.5650));
            modulation.put("0010", new AbstractMap.SimpleEntry(1.4142, 45.0000));
            modulation.put("0011", new AbstractMap.SimpleEntry(1.1180, 63.4350));
            modulation.put("0100", new AbstractMap.SimpleEntry(1.4142, 225.0000));
            modulation.put("0101", new AbstractMap.SimpleEntry(1.1180, 243.4350));
            modulation.put("0110", new AbstractMap.SimpleEntry(1.4142, 315.0000));
            modulation.put("0111", new AbstractMap.SimpleEntry(1.1180, 296.5650));
            modulation.put("1000", new AbstractMap.SimpleEntry(1.1180, 153.4350));
            modulation.put("1001", new AbstractMap.SimpleEntry(0.7071, 135.0000));
            modulation.put("1010", new AbstractMap.SimpleEntry(1.1180, 26.5650));
            modulation.put("1011", new AbstractMap.SimpleEntry(0.7071, 45.0000));
            modulation.put("1100", new AbstractMap.SimpleEntry(1.1180, 206.5650));
            modulation.put("1101", new AbstractMap.SimpleEntry(0.7071, 225.0000));
            modulation.put("1110", new AbstractMap.SimpleEntry(1.1180, 333.4350));
            modulation.put("1111", new AbstractMap.SimpleEntry(0.7071, 315.0000));
        }

    }

    public static final class Constellation16QAM2 extends Constellation {

        public Constellation16QAM2() {
            modulation.put("0000", new AbstractMap.SimpleEntry(1.4142, 45.0000));
            modulation.put("0001", new AbstractMap.SimpleEntry(3.0000, 0.0000));
            modulation.put("0010", new AbstractMap.SimpleEntry(1.4142, 135.0000));
            modulation.put("0011", new AbstractMap.SimpleEntry(3.0000, 90.0000));
            modulation.put("0100", new AbstractMap.SimpleEntry(1.4142, 225.0000));
            modulation.put("0101", new AbstractMap.SimpleEntry(3.0000, 180.0000));
            modulation.put("0110", new AbstractMap.SimpleEntry(1.4142, 315.0000));
            modulation.put("0111", new AbstractMap.SimpleEntry(3.0000, 270.0000));
            modulation.put("1000", new AbstractMap.SimpleEntry(5.0000, 270.0000));
            modulation.put("1001", new AbstractMap.SimpleEntry(4.2426, 315.0000));
            modulation.put("1010", new AbstractMap.SimpleEntry(5.0000, 180.0000));
            modulation.put("1011", new AbstractMap.SimpleEntry(4.2426, 225.0000));
            modulation.put("1100", new AbstractMap.SimpleEntry(5.0000, 90.0000));
            modulation.put("1101", new AbstractMap.SimpleEntry(4.2426, 135.0000));
            modulation.put("1110", new AbstractMap.SimpleEntry(5.0000, 0.0000));
            modulation.put("1111", new AbstractMap.SimpleEntry(4.2426, 45.0000));
        }

    }
}
