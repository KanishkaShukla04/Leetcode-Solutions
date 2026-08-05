import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build the forward adjacency matrix graph
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] inv : invocations) {
            graph[inv[0]].add(inv[1]);
        }
        
        // Step 2: Use BFS to mark all suspicious methods reachable from k
        boolean[] suspicious = new boolean[n];
        int[] queue = new int[n];
        int head = 0, tail = 0;
        
        queue[tail++] = k;
        suspicious[k] = true;
        
        while (head < tail) {
            int curr = queue[head++];
            for (int neighbor : graph[curr]) {
                if (!suspicious[neighbor]) {
                    suspicious[neighbor] = true;
                    queue[tail++] = neighbor;
                }
            }
        }
        
        // Step 3: Check if any non-suspicious method invokes a suspicious method
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!suspicious[u] && suspicious[v]) {
                // Removal boundary breached! Abort and return all methods.
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }
        
        // Step 4: Boundary is secure. Collect only non-suspicious methods.
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                remaining.add(i);
            }
        }
        return remaining;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna