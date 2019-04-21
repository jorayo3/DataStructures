import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        Palindrome palindrome = new Palindrome();
        assertTrue(palindrome.isPalindrome("ab", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertFalse(palindrome.isPalindrome("poop", offByOne));
        assertFalse(palindrome.isPalindrome("Za", offByOne));
        assertFalse(palindrome.isPalindrome("az", offByOne));
        assertTrue(palindrome.isPalindrome("%&%&", offByOne));
        assertTrue(palindrome.isPalindrome("z{", offByOne));
    }
}
