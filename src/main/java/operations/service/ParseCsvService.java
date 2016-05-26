package operations.service;

import java.io.BufferedReader;
import java.io.File;

/**
 * @author Nikelman Maksim
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import operations.tests.Tests;

public class ParseCsvService {

	public static List<Object[]> parseCSV(String fileName, boolean fromResources) {
		List<Object[]> operations = new ArrayList<>();
		BufferedReader br;
		try {
			if (fromResources == true) {
				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
				InputStream is = classloader.getResourceAsStream(fileName);
				br = new BufferedReader(new InputStreamReader(is));
			} else {
				br = new BufferedReader(new FileReader(new File(fileName)));
			}
			Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(br);
			for (CSVRecord record : records) {
				operations.add(new Object[] { (String) record.get(0), (String) record.get(1), (String) record.get(2),
						(String) record.get(3) });
			}
			// Можно заменить строки 32-36 на: 38-42
//			String line;
//			while ((line = br.readLine()) != null) {
//				List<String> params = Arrays.asList(line.split(";"));
//				operations.add(new Object[] { (String) params.get(0), (String) params.get(1), (String) params.get(2), (String) params.get(3) });
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return operations;
	}
}
