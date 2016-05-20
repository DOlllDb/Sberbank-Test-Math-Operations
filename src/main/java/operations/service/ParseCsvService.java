package operations.service;

/**
 * @author Nikelman Maksim
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
public class ParseCsvService {
	public static List<Object[]> parseCSV(String path) {
		List<Object[]> operations = new ArrayList<>();
		Reader in;
		try {
			in = new FileReader(path);
			Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(in);
			for (CSVRecord record : records) {
				operations.add(new Object[]{(String)record.get(0), (String)record.get(1), (String)record.get(2), (String)record.get(3)});
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return operations;
	}
}
