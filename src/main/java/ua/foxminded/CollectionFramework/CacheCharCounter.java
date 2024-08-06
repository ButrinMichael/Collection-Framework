package ua.foxminded.CollectionFramework;

public class CacheCharCounter implements Counter {

	private final Cache<String, String> cache;
	private Counter сounter;

	public CacheCharCounter(Cache<String, String> cache, Counter сounter) {
		this.cache = cache;
		this.сounter = сounter;
	}

	@Override
	public String countChars(String input) {
		if (!cache.containsKey(input)) {
			cache.put(input, сounter.countChars(input));
		}
		return cache.get(input);
	}

}

