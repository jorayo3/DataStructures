import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        OffByOne cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("aA"));
        assertFalse(palindrome.isPalindrome("ab"));
        assertTrue(palindrome.isPalindrome("ccacc"));
        assertTrue(palindrome.isPalindrome("wdabbadw"));
        assertTrue(palindrome.isPalindrome("*"));
        assertTrue(palindrome.isPalindrome("iOi"));
        assertFalse(palindrome.isPalindrome("abab"));
        assertTrue(palindrome.isPalindrome(";;P;;"));
        assertTrue(palindrome.isPalindrome("z{{z{", cc));
        assertFalse(palindrome.isPalindrome("aa", cc));
    }
}
