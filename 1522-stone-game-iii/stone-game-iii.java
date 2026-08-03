class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] stores the max relative score advantage for the player whose turn it is at index i
        int[] dp = new int[n + 1];
        // Starting from the last stone and work backwards
        for (int i = n - 1; i >= 0; i--) {
            int maxAdvantage = Integer.MIN_VALUE;
            int currentSum = 0;
            //Player can take 1, 2, or 3 stones
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                currentSum += stoneValue[i + k - 1];
                // Net score = points gained - opponent's optimal advantage from the remaining stones
                int currentAdvantage = currentSum - dp[i + k];
                maxAdvantage = Math.max(maxAdvantage, currentAdvantage);
            }
            dp[i] = maxAdvantage;
        }
        // dp[0] holds Alice's total optimal advantage over Bob for the full game
        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}
