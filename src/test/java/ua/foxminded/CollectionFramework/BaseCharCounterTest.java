package ua.foxminded.CollectionFramework;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class BaseCharCounterTest {
	private BaseCharCounter baseCharCounter;
	@BeforeEach
	void setUp() {
		baseCharCounter = new BaseCharCounter();
	}

	@Test
	void countChars_shouldReturnNothing_whenCountCharsNullInput() {
		String result = baseCharCounter.countChars(null);
		assertEquals("", result);
	}

	@Test
	void countChars_shouldReturnNewLine_whenEmptyInput() {
		String result = baseCharCounter.countChars("");
		assertEquals("\n", result);
	}

	@Test
	void countChars_shouldReturnformattetResult_whenNonEmptyInput() {
		String result = baseCharCounter.countChars("hello");
		assertEquals("hello\n\"h\" - 1\n\"e\" - 1\n\"l\" - 2\n\"o\" - 1", result);
	}

	@Test
	void countChars_shouldReturnformattetResult_whenCaseInput() {
		String result = baseCharCounter.countChars("HELLO");
		assertEquals("HELLO\n\"H\" - 1\n\"E\" - 1\n\"L\" - 2\n\"O\" - 1", result);
	}

	@Test
	void countChars_shouldReturnformattetResult_whenInputWithNumbers() {
		String result = baseCharCounter.countChars("e4l6lo2 2");
		assertEquals("e4l6lo2 2\n" + "\"e\" - 1\n" + "\"4\" - 1\n" + "\"l\" - 2\n" + "\"6\" - 1\n" + "\"o\" - 1\n"
				+ "\"2\" - 2\n" + "\" \" - 1", result);
	}

	@Test
	void countChars_shouldReturnformattetResult_whenTwoWordsInput() {
		String result = baseCharCounter.countChars("hello word");
		assertEquals("hello word\n" + "\"h\" - 1\n" + "\"e\" - 1\n" + "\"l\" - 2\n" + "\"o\" - 2\n" + "\" \" - 1\n"
				+ "\"w\" - 1\n" + "\"r\" - 1\n" + "\"d\" - 1", result);
	}

	@Test
	void countChars_shouldReturnformattetResult_whenThreWordsInput() {
		String result = baseCharCounter.countChars("hello m word");
		assertEquals("hello m word\n" + "\"h\" - 1\n" + "\"e\" - 1\n" + "\"l\" - 2\n" + "\"o\" - 2\n" + "\" \" - 2\n"
				+ "\"m\" - 1\n" + "\"w\" - 1\n" + "\"r\" - 1\n" + "\"d\" - 1", result);
	}

	@Test
	void countChars_shouldReturnformattetResult_whenSchortInput() {
		String result = baseCharCounter.countChars("Y");
		assertEquals("Y\n\"Y\" - 1", result);
	}

	@Test
	void countChars_shouldReturnformattetResult_whenSpecialCharacters() {
		String result = baseCharCounter.countChars("!@#$%^&*()");
		assertEquals(
				"!@#$%^&*()\n\"!\" - 1\n\"@\" - 1\n\"#\" - 1\n\"$\" - 1\n\"%\" - 1\n\"^\" - 1\n\"&\" - 1\n\"*\" - 1\n\"(\" - 1\n\")\" - 1",
				result);
	}

	@Test
	void countChars_shouldReturnformattetResult_whenSpecialCharactersWithLetters() {
		String result = baseCharCounter.countChars("!@cv#$%SD^&*()");
		assertEquals("!@cv#$%SD^&*()\n" + "\"!\" - 1\n" + "\"@\" - 1\n" + "\"c\" - 1\n" + "\"v\" - 1\n" + "\"#\" - 1\n"
				+ "\"$\" - 1\n" + "\"%\" - 1\n" + "\"S\" - 1\n" + "\"D\" - 1\n" + "\"^\" - 1\n" + "\"&\" - 1\n"
				+ "\"*\" - 1\n" + "\"(\" - 1\n" + "\")\" - 1", result);
	}
}
