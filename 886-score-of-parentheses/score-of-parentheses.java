class Solution {
    public int scoreOfParentheses(String s) {
        return F(s, 0, s.length());
    }
    private int F(String s, int i, int j) {
        int ans = 0, bal = 0;
        // Splitting the string into primitives
        for (int k = i; k < j; ++k) {
            bal += s.charAt(k) == '(' ? 1 : -1;
            if (bal == 0) {
                if (k - i == 1) {
                    ans++;
                } else {
                    ans += 2 * F(s, i + 1, k);
                }
                // Move start pointer for the next primitive
                i = k + 1; 
            }
        }
        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna