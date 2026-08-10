class Solution {
    public boolean winnerSquareGame(int n) {
        // dp[i] is true if the player who starts with i stones wins
        boolean[] dp = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            // Try removing every possible square number of stones
            for (int k = 1; k * k <= i; k++) {
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break; // Early exit: found a winning move!
                }
            }
        }
        
        return dp[n];
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna