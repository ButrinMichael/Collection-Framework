package ua.foxminded.CollectionFramework;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseCharCounter implements Counter {

	@Override
	public String countChars(String input) {
		if (input == null) {
			return "";
		}

		Map<Character, Integer> charCountMap = new LinkedHashMap<>();
		for (char c : input.toCharArray()) {
			charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
		}
		return toString(charCountMap, input);
	}

	public String toString(Map<Character, Integer> charCountMap, String input) {
		return input + "\n" + charCountMap.entrySet().stream()
				.map(entry -> "\"" + entry.getKey() + "\" - " + entry.getValue()).collect(Collectors.joining("\n"));
	}
}
