package pl.elka.pttw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.elka.pttw.constellation.Constellation;

import java.io.*;
import java.nio.charset.Charset;


public class App {
    public static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            log.error("Invalid number of arguments. Provide input file directory!");
        }
        String fileName = args[0];
        log.info("Reading file ...");
        String fileContent = readFile(fileName);
        log.info(fileContent);

        Modulator modulator = new Modulator(100.0, new Constellation.Constellation16QAM(), 4);
        modulator.modulate(fileContent);
    }

    private static final String readFile(String fileName) {
        String output = null;
        InputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            output = br.readLine();
        } catch (FileNotFoundException e) {
            log.error("Invalid file!");
        } catch (IOException e) {
            log.error("Invalid content!");
        }
        return output;

    }
}
