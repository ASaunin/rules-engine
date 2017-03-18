package org.asaunin.core;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString(exclude = "name")
public class Filter<T> {

	private String name;
	private T value;

	public Filter(String name, T value) {
		this.name = name;
		this.value = value;
	}

}
