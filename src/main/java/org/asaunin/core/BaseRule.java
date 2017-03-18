package org.asaunin.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter @Setter
@ToString
public class BaseRule {

	private Set<Filter> filters = new HashSet<>();

	public BaseRule(Set<Filter> filters) {
		this.filters = filters;
	}

	public static Set<Filter> createFiltersFromArray(String... args) {
		final Set<Filter> filters = new HashSet<>();
		for (int i = 0; i < args.length; i++) {
			if (null != args[i]) {
				final Filter<String> filter = new Filter<>(String.valueOf(i), args[i]);
				filters.add(filter);
			}
		}
		return filters;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof BaseRule)) return false;
		final BaseRule other = (BaseRule) o;
		final Object this$filters = this.getFilters();
		final Object other$filters = other.getFilters();
		return this$filters == null ? other$filters == null : this$filters.equals(other$filters);
	}

	@Override
	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $filters = this.getFilters();
		result = result * PRIME + ($filters == null ? 43 : $filters.hashCode());
		return result;
	}

}
