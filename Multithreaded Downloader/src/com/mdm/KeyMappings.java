package com.mdm;

public class KeyMappings<K, V> {
    private K key;
    private V value;

    public KeyMappings(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public String toString() {
        return "(" + key.toString() + ", " + value.toString() + ")";
    }
}

