package org.asaunin.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PowerSetTest {

	@Test
	public void generateShouldCreatePowerSet() throws Exception {
		final Set<String> data = new HashSet<>(Arrays.asList("A", "B", "C"));
		final Set<Set<String>> result = PowerSet.generate(data);

		assertThat(result)
				.containsSubsequence(new HashSet<>())
				.containsSubsequence(new HashSet<>(Collections.singletonList("A")))
				.containsSubsequence(new HashSet<>(Collections.singletonList("B")))
				.containsSubsequence(new HashSet<>(Collections.singletonList("C")))
				.containsSubsequence(new HashSet<>(Arrays.asList("A", "B")))
				.containsSubsequence(new HashSet<>(Arrays.asList("B", "C")))
				.containsSubsequence(new HashSet<>(Arrays.asList("A", "C")))
				.containsSubsequence(new HashSet<>(Arrays.asList("A", "B", "C")));
	}

}