package org.asaunin.utils;

import org.asaunin.core.Rule;

import java.io.IOException;
import java.util.Set;

public interface RulesReader {

	Set<Rule> read() throws IOException;

}
