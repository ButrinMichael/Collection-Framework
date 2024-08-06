package ua.foxminded.CollectionFramework;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CacheCharCounterTest {

	private CacheCharCounter cacheCharCounter;
	private CacheImpl mockCache;
	private BaseCharCounter mockCounter;

	@BeforeEach
	public void setUp() {
		mockCache = mock(CacheImpl.class);
		mockCounter = mock(BaseCharCounter.class);
		cacheCharCounter = new CacheCharCounter(mockCache, mockCounter);
	}

	@Test
	void countChars__shouldReturnEmptyValue_whenNullInput() {
		CacheCharCounter cacheCharCounter = new CacheCharCounter(null, null);
		assertThrows(NullPointerException.class, () -> cacheCharCounter.countChars(null));
	}

	@Test
	void countChars_shouldReturnNewLine_whenEmptyInput() {
		CacheImpl Cache = new CacheImpl();
		BaseCharCounter Counter = new BaseCharCounter();
		CacheCharCounter cacheCharCounter = new CacheCharCounter(Cache, Counter);
		assertEquals("\n", cacheCharCounter.countChars(""));

	}

	@Test
	void countChars_shouldReturnTrue_whenValueEquals() {
		CacheImpl Cache = new CacheImpl();
		BaseCharCounter Counter = new BaseCharCounter();
		CacheCharCounter cacheCharCounter = new CacheCharCounter(Cache, Counter);
		String result1 = cacheCharCounter.countChars("Gutten Morgen");
		String result2 = cacheCharCounter.countChars("Gutten Morgen");
		assertEquals(result1, result2);
	}

	@Test
	void countChars_shouldReturnFalse_whenValueNotEquals() {
		CacheImpl Cache = new CacheImpl();
		BaseCharCounter Counter = new BaseCharCounter();
		CacheCharCounter cacheCharCounter = new CacheCharCounter(Cache, Counter);
		String result1 = cacheCharCounter.countChars("hello");
		String result2 = cacheCharCounter.countChars("world");
		assertNotEquals(result1, result2);
	}

	@Test
	void countChars_shouldReturnFormattetValue_whenInCache() {
		String input = "abc";
		when(mockCache.containsKey(input)).thenReturn(true);
		when(mockCache.get(input)).thenReturn("cachedResult");
		String result = cacheCharCounter.countChars(input);
		verify(mockCache, times(1)).containsKey(input);
		verify(mockCache, times(1)).get(input);
		verify(mockCounter, times(0)).countChars(input);
		verify(mockCache, times(0)).put(input, result);
		assertEquals("cachedResult", result);
	}

	@Test
	public void countChars_shouldInvokeCacheImpl_whenNotInCache() {
		String input = "testInput";
		String expectedResult = "Expected Result";
		when(mockCache.containsKey(input)).thenReturn(false);
		when(mockCounter.countChars(input)).thenReturn(expectedResult);
		when(mockCache.get(input)).thenReturn(expectedResult);
		String result = cacheCharCounter.countChars(input);
		verify(mockCache, times(1)).containsKey(input);
		verify(mockCache, times(1)).get(input);
		verify(mockCounter, times(1)).countChars(input);
		verify(mockCache, times(1)).put(eq(input), anyString());
		assertEquals(expectedResult, result);
	}

	@Test
	void countChars_shouldCallBaseCharCounter_whenInputNotInCache() {
		String input = "testInput";
		when(mockCache.containsKey(input)).thenReturn(false);
		cacheCharCounter.countChars(input);
		verify(mockCounter, times(1)).countChars(input);
	}

	@Test
	void countChars_shouldNotCallBaseCharCounter_whenInputInCache() {
		String input = "testInput";
		when(mockCache.containsKey(input)).thenReturn(true);
		cacheCharCounter.countChars(input);
		verify(mockCounter, times(0)).countChars(input);
	}

}
