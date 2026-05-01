class Solution {
    public boolean isPalindrome(int x) {
        // negative numbers & numbers ending with 0 (except 0 itself)
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversedHalf = 0;

        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // for even digits: x == reversedHalf
        // for odd digits: x == reversedHalf/10 (middle digit ignored)
        return (x == reversedHalf) || (x == reversedHalf / 10);
    }
}