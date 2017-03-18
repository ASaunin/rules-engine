package org.asaunin.utils;

import org.asaunin.core.Rule;
import org.asaunin.core.RulesSet;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class CsvLoaderTest {

	@Test
	public void loadExistingFileShouldPass() throws Exception {
		final RulesSet expected = new RulesSet();
		expected.add(new Rule(1, 100, 10, Rule.createFiltersFromArray("AAA", "BBB", "CCC", null)));
		expected.add(new Rule(2, 80, 8, Rule.createFiltersFromArray("AAA", null, "CCC", "DDD")));
		expected.add(new Rule(3, 70, 7, Rule.createFiltersFromArray("BBB", null, "CCC", null)));
		expected.add(new Rule(4, 50, 5, Rule.createFiltersFromArray("CCC", "AAA", null, "CCC")));
		expected.add(new Rule(5, 10, 1, Rule.createFiltersFromArray(null, null, "AAA", null)));
		expected.add(new Rule(6, 0, 0, Rule.createFiltersFromArray(null, null, null, null)));


		final URL resource = getClass().getClassLoader().getResource("SampleData.csv");
		final RulesSet actual = new RulesSet(new CsvLoader(resource.getFile()));

		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void loadMissingFileShouldThrowException() {
		final Throwable thrown = catchThrowable(() -> new RulesSet(new CsvLoader("")));

		assertThat(thrown).isInstanceOf(IOException.class);
	}

}
