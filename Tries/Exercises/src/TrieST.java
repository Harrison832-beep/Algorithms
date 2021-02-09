public class TrieST<Value> {
    private static final int R = 256;

    private Node root = new Node();

    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null)
            x = new Node();

        if (d == key.length()) {
            x.value = val;
            return x;
        }

        char c = key.charAt(d);
        x.next[c] = put(x, key, val, d + 1);
        return x;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null)
            return null;

        return (Value) x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null)
            return null;

        if (d == key.length())
            return x;

        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);

    }

    public void delete(String key) {
        delete(root, key, 0);
    }

    private void delete(Node x, String key, int d) {
        if (x == null)
            return;

        if (d == key.length()) {
            x.value = null;
        }

        char c = key.charAt(d);
        delete(x.next[c], key, d + 1);

        if (emptyLink(x.next[c]))
            x.next[c] = null;
    }

    private boolean emptyLink(Node x) {
        for (int i = 0; i < R; i++)
            if (x.next[i] != null)
                return false;
        return true;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }


}
