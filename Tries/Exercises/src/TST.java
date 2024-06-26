public class TST<Value> {
    private Node root = null;

    private class Node {
        private Value val;
        private char c;
        private Node left, middle, right;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null)
            return null;
        return x.val;
    }

    private Node get(Node x, String key, int d) {
        char c = key.charAt(d);
        if (x == null) {
            return null;
        }

        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else if (d < key.length() - 1) {
            return get(x.middle, key, d + 1);
        } else {
            return x;
        }

    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }

        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.middle = put(x.middle, key, val, d + 1);
        } else {
            x.val = val;
        }

        return x;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public static void main(String[] args) {
        TST<Integer> tst = new TST<>();

        tst.put("she", 0);
        tst.put("sells", 1);
        tst.put("sea", 2);
        tst.put("shells", 3);
        tst.put("by", 4);
        tst.put("the", 5);
        tst.put("sea", 6);
        tst.put("shore", 7);


        assert (tst.get("she") == 0);
        assert (tst.get("sells") == 1);
        assert (tst.get("sea") == 6);
        assert (tst.get("shells") == 3);
        assert (tst.get("by") == 4);
        assert (tst.get("the") == 5);
        assert (tst.get("shore") == 7);
    }

}
