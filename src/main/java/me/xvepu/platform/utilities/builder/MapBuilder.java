package me.xvepu.platform.utilities.builder;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {
    private final Map<K, V> map;
    public MapBuilder() {
        this.map = new HashMap<>();
    }
    public MapBuilder<K,V> put(K key, V value) {
        map.put(key, value);
        return this;
    }
    public boolean contains(K key) {
        return map.containsKey(key);
    }
    public Map<K, V> build() {
        return map;
    }
}
