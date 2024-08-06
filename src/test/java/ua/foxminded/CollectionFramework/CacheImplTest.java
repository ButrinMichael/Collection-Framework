package ua.foxminded.CollectionFramework;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;

public class CacheImplTest {
	private CacheImpl cache;

	@BeforeEach
	void setUp() {
		cache = new CacheImpl();
	}

	@Test
	void get_shouldReturnValue_whenExistingKey() {
		cache.put("key", "value");
		assertEquals("value", cache.get("key"));
	}

	@Test
	void get_shouldReturnDefault_whenExistingKeyWithDefaultValue() {
		String result = cache.get("nonExistingKey");
		assertEquals(null, result);
	}

	@Test
	void containsKey_shouldReturnTrue_whenKeyExist() {
		cache.put("key1", "value1");
		assertTrue(cache.containsKey("key1"));
	}

	@Test
	void containsKey_shouldReturnFalse_whenKeyExist() {
		assertFalse(cache.containsKey("nonExistingKey"));
	}

	@Test
	void put_shouldReturnTrue_whenKeyAdded() {
		cache.put("key", "value");
		assertTrue(cache.containsKey("key"));
	}

	@Test
	void put_shouldReturnLastValue_whenValueOverwriteExistingValue() {
		cache.put("key1", "value1");
		cache.put("key1", "newValue");
		assertEquals("newValue", cache.get("key1"));
	}

	@Test
	void get_shouldReturnNull_whenNonExistingKeyWithoutDefaultValue() {
		assertNull(cache.get("nonExistingKey"));
	}

	@Test
	void putAndGetAndContainsKey_shouldReturnValue_whenMultipleValues() {
		cache.put("key1", "value1");
		cache.put("key2", "value2");
		assertTrue(cache.containsKey("key1"));
		assertTrue(cache.containsKey("key2"));
		assertEquals("value1", cache.get("key1"));
		assertEquals("value2", cache.get("key2"));
	}

	@Test
	void putAndGetAndContainsKey_shouldReturnEmptyValue_whenInputValueEmpty() {
		cache.put("key", "");
		assertTrue(cache.containsKey("key"));
		assertEquals("", cache.get("key"));
	}
}
