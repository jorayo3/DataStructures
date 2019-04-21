public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> l = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            l.addLast(word.charAt(i));
        }
        return l;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> pal = wordToDeque(word);
        if (word.length() <= 1) {
            return true;
        } else {
            char front, back;
            for (int i = 0; i < word.length() / 2; i++) {
                front = pal.removeFirst();
                back = pal.removeLast();
                if (front != back) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> pal = wordToDeque(word);
        if (word.length() <= 1) {
            return true;
        } else {
            char front, back;
            for (int i = 0; i < word.length() / 2; i++) {
                front = pal.removeFirst();
                back = pal.removeLast();
                if (!cc.equalChars(front, back)) {
                    return false;
                }
            }
            return true;
        }

    }
}
