import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B{
    private Node root;

    public MyTrieSet() {
        root = new Node('0', false);
    }

    @Override
    public boolean contains(String key) {
        if (key == null) {
            return false;
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (curr.map.containsKey(c)) {
                curr = curr.map.get(c);
            } else {
                return false;
            }
        }
        return curr.isItem;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            prefix = "";
        }
        List<String> list = new ArrayList<>();
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (root.map.containsKey(c)) {
                curr = curr.map.get(c);
            } else {
                return null;
            }
        }
        list = keysWithPrefixHelper(curr, list, prefix);
        return list;
    }

    private List<String> keysWithPrefixHelper(Node node ,List<String> list, String prefix) {
        String newPrefix;
        HashMap<Character, Node> map = node.map;
        Node currNode;
        for (char key : map.keySet()) {
            newPrefix = prefix + key;
            currNode = map.get(key);
            if (currNode.isItem) {
                list.add(newPrefix);
            }
            list = keysWithPrefixHelper(currNode, list, newPrefix);
        }
        return list;
    }

    @Override
    public void clear() {
        root.map.clear();
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isItem = true;
    }

    private class Node {
        private char c;
        public boolean isItem;
        public HashMap<Character, Node> map;

        public Node(char c, boolean isItem) {
            this.c = c;
            this.isItem = isItem;
            map = new HashMap();
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
