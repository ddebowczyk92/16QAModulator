package pl.elka.pttw.constellation;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dominik on 03/06/2017.
 */
public class Constellation {

    public interface Modulation {

        AbstractMap.SimpleEntry getAmplitudeAndPhase(String grayCode);
    }

    public static final class Constellation16QAM implements Modulation {
        private final Map<String, AbstractMap.SimpleEntry<Double, Double>> modulation;

        public Constellation16QAM() {
            modulation = new HashMap<>();
            modulation.put("0000", new AbstractMap.SimpleEntry(1.4142, 135.0000));
            modulation.put("0001", new AbstractMap.SimpleEntry(1.1180, 116.5650));
            modulation.put("0010", new AbstractMap.SimpleEntry(1.4142,  45.0000));
            modulation.put("0011", new AbstractMap.SimpleEntry(1.1180,  63.4350));
            modulation.put("0100", new AbstractMap.SimpleEntry(1.4142, 225.0000));
            modulation.put("0101", new AbstractMap.SimpleEntry(1.1180, 243.4350));
            modulation.put("0110", new AbstractMap.SimpleEntry(1.4142, 315.0000));
            modulation.put("0111", new AbstractMap.SimpleEntry(1.1180, 296.5650));
            modulation.put("1000", new AbstractMap.SimpleEntry(1.1180, 153.4350));
            modulation.put("1001", new AbstractMap.SimpleEntry(0.7071, 135.0000));
            modulation.put("1010", new AbstractMap.SimpleEntry(1.1180,  26.5650));
            modulation.put("1011", new AbstractMap.SimpleEntry(0.7071,  45.0000));
            modulation.put("1100", new AbstractMap.SimpleEntry(1.1180, 206.5650));
            modulation.put("1101", new AbstractMap.SimpleEntry(0.7071, 225.0000));
            modulation.put("1110", new AbstractMap.SimpleEntry(1.1180, 333.4350));
            modulation.put("1111", new AbstractMap.SimpleEntry(0.7071, 315.0000));
        }

        @Override
        public AbstractMap.SimpleEntry getAmplitudeAndPhase(String grayCode){
            return modulation.get(grayCode);
        }
    }
}
