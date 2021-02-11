/*
    It was the best of times,
    it was the worst of times,
    it was the age of wisdom,
    it was the age of foolishness,

    age 3-4
    best 1
    foolishness 4
    it 1-4
    of 1-4
    the 1-4
    times 1-2
    was 1-4
    wisdom 4
    worst 2
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieST;

public class BookIndexer {
    public static void main(String[] args) {
        In in = new In(args[0]);
        TrieST<SET<Integer>> trie = new TrieST<>();

        for (int i = 1; !in.isEmpty(); i++) {
            String line = in.readLine();
            line = removePunctuations(line);
            String[] words = line.split(" ");

            for (String word : words) {
                word = word.toLowerCase();

                if (!trie.contains(word)) {
                    SET<Integer> set = new SET<>();
                    trie.put(word, set);
                }
                SET<Integer> set = trie.get(word);
                set.add(i);
                trie.put(word, set);
            }

        }

        for (Object key : trie.keys()) {
            StdOut.print(key + " ");
            SET<Integer> set = trie.get((String) key);
            for (int i : set)
                StdOut.print(i + " ");
            StdOut.println();
        }

    }

    private static String removePunctuations(String line) {
        line = line.replace(",", "");
        line = line.replace(".", "");
        line = line.replace("?", "");
        line = line.replace("!", "");
        return line;
    }
}
