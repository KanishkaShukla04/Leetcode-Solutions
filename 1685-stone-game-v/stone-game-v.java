import java.util.Arrays;

class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + stoneValue[i];
        }

        // memo[i][j] stores the maximum score Alice can get from subarray [i...j]
        int[][] memo = new int[n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return solve(0, n - 1, pref, memo);
    }

    private int solve(int i, int j, int[] pref, int[][] memo) {
        if (i == j) return 0; // Only 1 stone left, game ends
        if (memo[i][j] != -1) return memo[i][j];

        int maxScore = 0;

        // Try splitting at every possible point k between i and j-1
        for (int k = i; k < j; k++) {
            int leftSum = pref[k + 1] - pref[i];
            int rightSum = pref[j + 1] - pref[k + 1];

            if (leftSum < rightSum) {
                // Bob throws away the right row, Alice keeps the left row
                maxScore = Math.max(maxScore, leftSum + solve(i, k, pref, memo));
            } else if (leftSum > rightSum) {
                // Bob throws away the left row, Alice keeps the right row
                maxScore = Math.max(maxScore, rightSum + solve(k + 1, j, pref, memo));
            } else {
                // Sums are equal, Alice chooses which row to keep
                int keepLeft = leftSum + solve(i, k, pref, memo);
                int keepRight = rightSum + solve(k + 1, j, pref, memo);
                maxScore = Math.max(maxScore, Math.max(keepLeft, keepRight));
            }
        }

        return memo[i][j] = maxScore;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna