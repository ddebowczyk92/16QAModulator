package pl.elka.pttw;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.elka.pttw.constellation.Constellation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by dominik on 03/06/2017.
 */
public class Modulator {

    private static final Logger log = LoggerFactory.getLogger(Modulator.class);

    private final Double carrierFrequency;
    private final Constellation constellation;
    private final Integer bitsPerBaud;

    private LinkedList<String> grayCodeData;

    public Modulator(Double carrierFrequency, Constellation constellation, Integer bitsPerBaud) {
        this.carrierFrequency = carrierFrequency;
        this.constellation = constellation;
        this.bitsPerBaud = bitsPerBaud;
    }


    public LinkedList<Double> modulate(String inputData) {
        inputData = padStringIfNecessery(inputData);
        log.info(inputData);
        this.grayCodeData = splitIntoGrayCodeStrings(inputData);

        double ampl, phase, analog;
        LinkedList<Double> analogData = new LinkedList();

        for (int i = 0; i < grayCodeData.size(); i++) {
            ampl = (double) constellation.getAmplitudeAndPhase(grayCodeData.get(i)).getKey();
            phase = (double) constellation.getAmplitudeAndPhase(grayCodeData.get(i)).getValue();
            log.debug(i + 1 + ". ampl - " + ampl + ", phase - " + phase);
            log.debug("analog - ");
            for (double x = Math.PI / 2; x <= 2 * Math.PI; x = x + Math.PI / 2) {
                analog = Math.round(ampl * Math.sin(x + Math.toRadians(phase)) * this.carrierFrequency) / this.carrierFrequency;
                analogData.add(analog);
                log.debug(analog + ", ");
            }

        }
        return analogData;
    }

    private String padStringIfNecessery(String inputData) {
        Integer pad = inputData.length() % 4;
        if (pad != 0) {
            Integer newStringLength = inputData.length() + (4 - pad);
            return Strings.padEnd(inputData, newStringLength, '0');
        }
        return inputData;
    }

    private LinkedList<String> splitIntoGrayCodeStrings(String data) {
        List<String> source = Arrays.asList(data.split(""));
        Integer size = source.size();
        Integer fullChunks = (size - 1) / bitsPerBaud;
        return IntStream.range(0, fullChunks + 1).mapToObj(
                n -> source.subList(n * bitsPerBaud, n == fullChunks ? size : (n + 1) * bitsPerBaud))
                .map(n -> {
                    StringBuilder builder = new StringBuilder();
                    n.forEach(s -> builder.append(s));
                    return builder.toString();
                }).collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<String> getGrayCodeData() {
        return grayCodeData;
    }
}
