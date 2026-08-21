import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int numSubsets = 1 << n;
        long[] lcmValues = new long[numSubsets];
        int[] subsetSizes = new int[numSubsets];
        
        // Precompute LCM and subset sizes for all coin combinations
        lcmValues[0] = 1;
        for (int i = 1; i < numSubsets; i++) {
            long currentLcm = 1;
            int size = 0;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    size++;
                    if (currentLcm == 1) {
                        currentLcm = coins[j];
                    } else {
                        currentLcm = lcm(currentLcm, coins[j]);
                    }
                }
            }
            lcmValues[i] = currentLcm;
            subsetSizes[i] = size;
        }

        // Binary Search on the Answer
        long low = 1;
        long high = 1L * k * Arrays.stream(coins).min().getAsInt();
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (countMultiples(mid, numSubsets, lcmValues, subsetSizes) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private long countMultiples(long mid, int numSubsets, long[] lcmValues, int[] subsetSizes) {
        long count = 0;
        // Skip 0 (empty subset)
        for (int i = 1; i < numSubsets; i++) {
            long multiples = mid / lcmValues[i];
            if (subsetSizes[i] % 2 == 1) {
                count += multiples;
            } else {
                count -= multiples;
            }
        }
        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna