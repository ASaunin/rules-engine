package org.asaunin.core;

import org.asaunin.utils.RulesReader;

import java.util.Optional;
import java.util.Set;

public interface RulesEngine {

	void load(RulesReader reader) throws Exception;

	Optional<Rule> find(Set<Filter> filters);

}
