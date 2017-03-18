package org.asaunin.utils;

import com.opencsv.CSVReader;
import org.asaunin.core.Filter;
import org.asaunin.core.Rule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CsvLoader implements RulesReader {

	private File file;

	public CsvLoader(String fileName) throws FileNotFoundException {
		this(fileName != null ? new File(fileName) : null);
	}

	public CsvLoader(File file) throws FileNotFoundException {
		this.file = file;
	}

	public Set<Rule> read() throws IOException {

		final Set<Rule> rules = new HashSet<>();
		try (FileReader fileReader = new FileReader(file)) {
			final CSVReader csvReader = new CSVReader(fileReader);

			final String[] header = csvReader.readNext();
			if (null == header) {
				throw new IOException(String.format("CSV '%s' has no header", file));
			}

			for (String[] line; null != (line = csvReader.readNext()); ) {
				final Rule rule = new Rule();
				final Set<Filter> filters = new HashSet<>();
				int index = 0;
				for (int col = 0; col < header.length; col++) {
					switch (header[col]) {
						case "RuleId":
							rule.setId(Integer.parseInt(line[col]));
							break;
						case "Priority":
							rule.setPriority(Integer.parseInt(line[col]));
							break;
						case "OutputValue":
							rule.setValue(Integer.parseInt(line[col]));
							break;
						default:
							if (header[col].contains("Filter")) {
								if (!"<ANY>".equals(line[col])) {
									filters.add(new Filter<>(String.valueOf(index), line[col]));
								}
								index++;
							}
					}
				}
				rule.setFilters(filters);
				rules.add(rule);
			}
		}
		return rules;
	}

}
