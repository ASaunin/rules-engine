package org.asaunin.core;

import lombok.NoArgsConstructor;
import org.asaunin.utils.PowerSet;
import org.asaunin.utils.RulesReader;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public class RulesSet extends HashSet<Rule> implements RulesEngine {

	public RulesSet(RulesReader reader) throws IOException {
		load(reader);
	}

	@Override
	public void load(RulesReader reader) throws IOException {
		clear();
		addAll(reader.read());
	}

	@Override
	public Optional<Rule> find(Set<Filter> filters) {
		final Set<BaseRule> filtered = PowerSet.generate(filters)
				.stream()
				.map(BaseRule::new)
				.collect(Collectors.toSet());

		return stream()
				.filter(r -> r.isIn(filtered))
				.max(Comparator.comparingInt(Rule::getPriority));
	}

}
