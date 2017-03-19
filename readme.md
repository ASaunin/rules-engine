# Rules engine
Just a "take home" task after an interview

## Requirements & Features

- Load data from file to memory collection of strongly typed POJO entities
- Create library that will be finding best matching rule and its output Value for set of filtering values:
- Write Unit Tests
- Taking into account that collection can store 1000+ records it should be performant enough.
- Source code should be reusable as much as possible to support different Rules models (with different list of filters, different type for each filter). For instance, to write another set of strongly typed rules with Filters: Filter1(int), Filter2(bool), Filter3(string).

## Classes description

- `CsvLoader` reads rules set from *.csv file implements `RulesReader` interface for reusable approach
- `PowerSet` implements filters subset decomposition
- `Filter` is generic class. Different filter options should have different names
- `BasicRule` contains group of filters. It is used as a parent for `Rule` class, that have additional properties: id, priority & value
- `RulesSet` is the main class, that implements `RulesEngine` interface

## Code examples (for the proper information see unit tests)

- Load data from csv:
```
final URL resource = getClass().getClassLoader().getResource("SampleData.csv");
final RulesSet actual = new RulesSet(new CsvLoader(resource.getFile()));
 ```
- Create rules set manually:
```
final RulesSet data = new RulesSet();
data.add(new Rule(1, 100, 10, Rule.createFiltersFromArray("AAA", "BBB", "CCC", null)));
data.add(new Rule(2, 80, 8, Rule.createFiltersFromArray("AAA", null, "CCC", "DDD")));
data.add(new Rule(3, 70, 7, Rule.createFiltersFromArray("BBB", null, "CCC", null)));
data.add(new Rule(4, 50, 5, Rule.createFiltersFromArray("CCC", "AAA", null, "CCC")));
data.add(new Rule(5, 10, 1, Rule.createFiltersFromArray(null, null, "AAA", null)));
data.add(new Rule(6, 0, 0, Rule.createFiltersFromArray(null, null, null, null)));
```
- Generic filters:
```
final Filter intFilter = new Filter("0", 1);
final Filter boolFilter = new Filter("1", true);
final Filter strFilter = new Filter("2", "Yohoho");

final RulesSet data = new RulesSet();
final Set<Filter> filters = new HashSet<>();
filters.add(intFilter);
filters.add(boolFilter);
filters.add(strFilter);
data.add(new Rule(1, 100, 100, filters));
```
- Find rule example:
```
final RulesSet data = new RulesSet();
data.add(new Rule(1, 100, 10, Rule.createFiltersFromArray("AAA", "BBB", "CCC", null)));
final Optional<Rule> rule = data.find(Rule.createFiltersFromArray("AAA", "AAA", "AAA", "AAA"));
assertThat(rule.isPresent()).isFalse();
```

## Dependencies

1. Make sure you have [Java](http://www.java.com/) installed on your system, if not follow the vendor instructions for installing them on your operating system.
2. Enable [LOMBOK](https://projectlombok.org/) plugin on your IDE, follow the vendor instructions for installing LOMBOK for your IDE.
  * For eclipse follow [this instruction](https://projectlombok.org/download.html)
  * For IntelliJ Idea:
    * Windows: click Settings -> Plugins -> Browse repositories for "lombok" -> Install. Restart IntelliJ.
    * OSX: click IntelliJ IDEA -> Preferences -> Plugins -> Browse repositories for "lombok" -> Install. Restart Intellij.

3. Go to "Annotation Processors"
    * Windows: click Settings -> Build, Execution, Deployment -> Annotation Processors. Set "Enable annotation processing".
    * OSX: click Intellij IDEA -> Preferences -> Build, Execution, Deployment -> Annotation Processors. Set "Enable annotation processing".
