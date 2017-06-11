package pl.elka.pttw;

import org.jfree.ui.RefineryUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.elka.pttw.constellation.Constellation;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class App {
	public static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		try {
			Properties prop = new Properties();
			InputStream input = null;
			input = new FileInputStream("config.properties");
			prop.load(input);

			int constellationNumber = Integer.parseInt(prop.getProperty("constellation"));
			log.info("Reading file ...");
			String fileContent = readFile(prop.getProperty("inputFileName"));
			log.info(fileContent);
			Modulator modulator = null;
			if (constellationNumber == 1) {
				modulator = new Modulator(100.0, new Constellation.Constellation16QAM1(), 4);
				writeFile(modulator.modulate(fileContent));
			} else if (constellationNumber == 2) {
				modulator = new Modulator(100.0, new Constellation.Constellation16QAM2(), 4);
				writeFile(modulator.modulate(fileContent));
			} else {
				System.out.println("Wrong constellation number");
			}
			
			ConstellationChart chart = new ConstellationChart("16QAM Constellation", "16QAM Constellation", modulator.XYDataset(fileContent));
			chart.pack();
			RefineryUtilities.centerFrameOnScreen(chart);
			chart.setVisible(true);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	private static final String writeFile(List<Double> doubleDataList) {
		Path out = Paths.get("output.txt");
		List<String> analogData = new ArrayList<String>();
		for (Double d : doubleDataList) {
			analogData.add(d.toString());
		}
		try {
			Files.write(out, analogData, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
