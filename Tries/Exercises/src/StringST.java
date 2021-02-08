public interface StringST<Value> {
    Value get(String key);

    void put(String key, Value val);

    boolean contains(String key);
}
