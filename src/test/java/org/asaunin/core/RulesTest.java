package org.asaunin.core;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RulesTest {

	@Test
	public void getFiltersFromArrayShouldReturnAppropriateFilterSet() throws Exception {
		final Set<Filter> expected = new HashSet<>();
		expected.add(new Filter<>("0", "AAA"));
		//expected.add(new Filter<>("1", null)); //Redundant
		expected.add(new Filter<>("2", "BBB"));
		expected.add(new Filter<>("3", "CCC"));

		final Set<Filter> actual = Rule.createFiltersFromArray("AAA", null, "BBB", "CCC");
		assertThat(actual).contains(new Filter<>("0", "AAA"));
		assertThat(actual).doesNotContain(new Filter<>("1", "AAA"));
		assertThat(actual).isEqualTo(expected);
	}

}