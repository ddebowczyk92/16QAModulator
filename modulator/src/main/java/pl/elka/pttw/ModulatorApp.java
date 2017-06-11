package pl.elka.pttw;

import org.jfree.ui.RefineryUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.elka.pttw.common.IOCommons;
import pl.elka.pttw.constellation.Constellation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Hello world!
 */
public class ModulatorApp {

    public static final Logger log = LoggerFactory.getLogger(pl.elka.pttw.ModulatorApp.class);

    public static void main(String[] args) {

        try {
            Properties prop = new Properties();
            InputStream input;
            input = new FileInputStream("config.properties");
            prop.load(input);

            int constellationNumber = Integer.parseInt(prop.getProperty("constellation"));
            Constellation constellation = null;
            log.info("Reading file ...");
            String fileContent = IOCommons.readFile(prop.getProperty("inputFileName"));
            log.info(fileContent);
            Modulator modulator = null;
            if (constellationNumber == 1 || constellationNumber == 2) {
                constellation = constellationNumber == 1 ? new Constellation.Constellation16QAM1() : new Constellation.Constellation16QAM2();

                modulator = new Modulator(10000.0, constellation, 4);
                LinkedList<Double> analogData = modulator.modulate(fileContent);
                IOCommons.writeFile(analogData);
                ConstellationChart chart = new ConstellationChart("16QAM Constellation",
                        "16QAM Constellation", constellation, modulator.getGrayCodeData());
                chart.pack();
                RefineryUtilities.centerFrameOnScreen(chart);
                chart.setVisible(true);
            } else {
                log.info("Wrong constellation number");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

