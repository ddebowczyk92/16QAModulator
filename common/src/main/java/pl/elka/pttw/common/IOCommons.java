package pl.elka.pttw.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dominik on 11/06/2017.
 */
public class IOCommons {

    private static final Logger log = LoggerFactory.getLogger(IOCommons.class);

    public static final String readFile(String fileName) {
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

    public static final String writeFile(List<Double> doubleDataList) {
        Path out = Paths.get("output.txt");
        List<String> analogData = new ArrayList<String>();
        for (Double d : doubleDataList) {
            analogData.add(d.toString());
        }
        try {
            Files.write(out, analogData, Charset.forName("UTF-8"));
        } catch (IOException e) {
            log.error("File write error", e);
        }
        return null;

    }
}
