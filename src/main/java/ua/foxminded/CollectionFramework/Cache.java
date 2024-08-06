package ua.foxminded.CollectionFramework;

public interface Cache<R, V> {

	V get(R key);

	boolean containsKey(R key);

	void put(R key, V value);
}
