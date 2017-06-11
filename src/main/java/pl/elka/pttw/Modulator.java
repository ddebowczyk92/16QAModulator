package pl.elka.pttw;

import com.google.common.base.Strings;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.elka.pttw.constellation.Constellation;

import java.util.ArrayList;
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
    
	public XYDataset XYDataset(String inputData) {
        inputData = padStringIfNecessery(inputData);
        List<String> grayCodeData = splitIntoGrayCodeStrings(inputData);
        double ampl, phase, x, y;
        
        final XYSeries constellation = new XYSeries("constellation");
        for(int i=0; i<grayCodeData.size(); i++){
        	ampl=(double) modulation.getAmplitudeAndPhase(grayCodeData.get(i)).getKey();
        	phase=(double) modulation.getAmplitudeAndPhase(grayCodeData.get(i)).getValue();
        	x=ampl*Math.cos(Math.toRadians(phase));
        	y=ampl*Math.sin(Math.toRadians(phase));
        	constellation.add(x, y);
        }
        
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(constellation);
		return dataset;
	}
    
    public List<Double> modulate(String inputData) {
        inputData = padStringIfNecessery(inputData);
        log.info(inputData);
        List<String> grayCodeData = splitIntoGrayCodeStrings(inputData);
        double ampl, phase, analog;
        List<Double> analogData =  new ArrayList<Double>();
        
        for(int i=0; i<grayCodeData.size(); i++){
        	ampl=(double) modulation.getAmplitudeAndPhase(grayCodeData.get(i)).getKey();
        	phase=(double) modulation.getAmplitudeAndPhase(grayCodeData.get(i)).getValue();
        	System.out.println(i+1+". ampl - " +ampl + ", phase - " +phase);
        	System.out.print("analog - ");
        	for(double x=Math.PI/2; x<=2*Math.PI; x=x+Math.PI/2){
        		analog = (double)Math.round(ampl*Math.sin(x+Math.toRadians(phase))* 10000) / 10000;
        		analogData.add(analog);
        		System.out.print(analog + ", ");
        	}
        	System.out.println();
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
