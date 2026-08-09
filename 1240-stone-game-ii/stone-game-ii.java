import java.util.*;

class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // suffixSums[i] stores the total number of stones from index i to the end
        int[] suffixSums = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSums[i] = suffixSums[i + 1] + piles[i];
        }
        
        // memo[i][m] stores the max stones a player can get starting at index i with current M
        int[][] memo = new int[n][n + 1];
        
        return dfs(0, 1, suffixSums, memo, n);
    }
    
    private int dfs(int i, int m, int[] suffixSums, int[][] memo, int n) {
        // Base case: if we can take all the remaining piles, take them all!
        if (i + 2 * m >= n) {
            return suffixSums[i];
        }
        
        if (memo[i][m] > 0) {
            return memo[i][m];
        }
        
        int minOpponentStones = Integer.MAX_VALUE;
        
        // Try taking X piles where 1 <= X <= 2M
        for (int x = 1; x <= 2 * m; x++) {
            // The opponent will play optimally from the next state
            int opponentStones = dfs(i + x, Math.max(m, x), suffixSums, memo, n);
            minOpponentStones = Math.min(minOpponentStones, opponentStones);
        }
        
        // Our max stones = total remaining stones - opponent's optimal stones
        memo[i][m] = suffixSums[i] - minOpponentStones;
        return memo[i][m];
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna