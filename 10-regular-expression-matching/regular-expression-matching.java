class Solution {
    public boolean isMatch(String s, String p) {
        Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];
        return helper(0, 0, s, p, dp);
    }

    private boolean helper(int i, int j, String s, String p, Boolean[][] dp) {
        if (dp[i][j] != null) return dp[i][j];

        // If pattern finished
        if (j == p.length()) {
            return dp[i][j] = (i == s.length());
        }

        // Check current match
        boolean firstMatch = (i < s.length() &&
                (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

        // If next char is '*'
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // Skip OR use *
            return dp[i][j] = (helper(i, j + 2, s, p, dp) ||
                               (firstMatch && helper(i + 1, j, s, p, dp)));
        } else {
            // Normal move
            return dp[i][j] = firstMatch && helper(i + 1, j + 1, s, p, dp);
        }
    }
}