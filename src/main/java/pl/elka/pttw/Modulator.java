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
    private final Constellation.Modulation modulation;
    private final Integer bitsPerBaud;

    private String inputData;

    public Modulator(Double carrierFrequency, Constellation.Modulation modulation, Integer bitsPerBaud) {
        this.carrierFrequency = carrierFrequency;
        this.modulation = modulation;
        this.bitsPerBaud = bitsPerBaud;
    }

    public String modulate(String inputData) {
        inputData = padStringIfNecessery(inputData);
        log.info(inputData);
        List<String> grayCodeData = splitIntoGrayCodeStrings(inputData);

        return null;
    }

    private String padStringIfNecessery(String inputData) {
        Integer pad = inputData.length() % 4;
        if (pad != 0) {
            Integer newStringLength = inputData.length() + (4 - pad);
            return Strings.padEnd(inputData, newStringLength, '0');
        }
        return inputData;
    }

    private LinkedList<Double> analogSignal(LinkedList<String> dataInGrayCode) {
        LinkedList<Double> output = new LinkedList<>();

        return output;
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


}
