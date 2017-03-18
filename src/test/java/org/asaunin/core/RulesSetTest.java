package org.asaunin.core;

import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class RulesSetTest {

	@Test
	public void findShouldMatchExpectedBehavior() throws Exception {
		final RulesSet data = new RulesSet();
		data.add(new Rule(1, 100, 10, Rule.createFiltersFromArray("AAA", "BBB", "CCC", null)));
		data.add(new Rule(2, 80, 8, Rule.createFiltersFromArray("AAA", null, "CCC", "DDD")));
		data.add(new Rule(3, 70, 7, Rule.createFiltersFromArray("BBB", null, "CCC", null)));
		data.add(new Rule(4, 50, 5, Rule.createFiltersFromArray("CCC", "AAA", null, "CCC")));
		data.add(new Rule(5, 10, 1, Rule.createFiltersFromArray(null, null, "AAA", null)));
		data.add(new Rule(6, 0, 0, Rule.createFiltersFromArray(null, null, null, null)));

		final Optional<Rule> rule1 = data.find(Rule.createFiltersFromArray("AAA", "BBB", "CCC", null));
		assertThat(rule1.isPresent()).isTrue();
		assertThat(rule1.get()).hasFieldOrPropertyWithValue("value", 10);

		final Optional<Rule> rule2 = data.find(Rule.createFiltersFromArray("AAA", "BBB", "CCC", "DDD"));
		assertThat(rule2.isPresent()).isTrue();
		assertThat(rule2.get()).hasFieldOrPropertyWithValue("value", 10);

		final Optional<Rule> rule3 = data.find(Rule.createFiltersFromArray("AAA", "AAA", "AAA", "AAA"));
		assertThat(rule3.isPresent()).isTrue();
		assertThat(rule3.get()).hasFieldOrPropertyWithValue("value", 1);

		final Optional<Rule> rule4 = data.find(Rule.createFiltersFromArray("BBB", "BBB", "BBB", "BBB"));
		assertThat(rule4.isPresent()).isTrue();
		assertThat(rule4.get()).hasFieldOrPropertyWithValue("value", 0);

		final Optional<Rule> rule5 = data.find(Rule.createFiltersFromArray("BBB", "CCC", "CCC", "CCC"));
		assertThat(rule5.isPresent()).isTrue();
		assertThat(rule5.get()).hasFieldOrPropertyWithValue("value", 7);
	}

	@Test
	public void checkGenericFilters() throws Exception {
		final Filter intFilter = new Filter("0", 1);
		final Filter boolFilter = new Filter("1", true);
		final Filter strFilter = new Filter("2", "Yohoho");

		final RulesSet data = new RulesSet();
		final Set<Filter> filters = new HashSet<>();
		filters.add(intFilter);
		filters.add(boolFilter);
		filters.add(strFilter);
		data.add(new Rule(1, 100, 100, filters));
		data.add(new Rule(2, 0, 0, Rule.createFiltersFromArray(null, null, null)));

		final Optional<Rule> rule1 = data.find(filters);
		assertThat(rule1.isPresent()).isTrue();
		assertThat(rule1.get()).hasFieldOrPropertyWithValue("value", 100);

		final Optional<Rule> rule2 = data.find(new HashSet<>(Collections.singletonList(intFilter)));
		assertThat(rule2.isPresent()).isTrue();
		assertThat(rule2.get()).hasFieldOrPropertyWithValue("value", 0);
	}

	@Test
	public void checkIfNoUnfilteredRuleFindReturnsEmptyOptional() throws Exception {
		final RulesSet data = new RulesSet();
		data.add(new Rule(1, 100, 10, Rule.createFiltersFromArray("AAA", "BBB", "CCC", null)));

		final Optional<Rule> rule1 = data.find(Rule.createFiltersFromArray("AAA", "AAA", "AAA", "AAA"));
		assertThat(rule1.isPresent()).isFalse();
	}

}