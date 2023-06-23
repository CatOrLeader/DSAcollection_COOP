package datastructures.maps;

/**
 * Interface with all canonical functions for MapADT.
 *
 * @param <K> - object which will be treated as key in HashMap.
 * @param <V> - object which will be treated as value in HashMap.
 */
interface MapADT<K, V> {
    int size();

    boolean isEmpty();

    V get(K key);

    void put(K key, V value);

    void remove(K key);

    Iterable<K> keySet();

    Iterable<V> values();

    Iterable<KeyValuePair<K, V>> entrySet();
}
