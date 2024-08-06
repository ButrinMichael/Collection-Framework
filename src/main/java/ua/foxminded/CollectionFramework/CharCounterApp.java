package ua.foxminded.CollectionFramework;

import java.util.Scanner;

public class CharCounterApp {

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter a phrase: ");
			String input = scanner.nextLine();
			CacheImpl cache = new CacheImpl();
			BaseCharCounter counter = new BaseCharCounter();
			CacheCharCounter cacheCharCounter = new CacheCharCounter(cache, counter);
			System.out.println(cacheCharCounter.countChars(input));
		}
	}
}
