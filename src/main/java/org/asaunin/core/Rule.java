package org.asaunin.core;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Rule extends BaseRule {

	private int id;
	private int priority;
	private int value;

	public Rule(int id, int priority, int value, Set<Filter> filters) {
		super(filters);
		this.id = id;
		this.priority = priority;
		this.value = value;
	}

	public boolean isIn(Set<BaseRule> rules) {
		return rules.stream()
				.anyMatch(r -> r.equals(this));
	}

}
