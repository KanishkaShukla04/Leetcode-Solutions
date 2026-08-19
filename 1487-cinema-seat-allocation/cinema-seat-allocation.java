import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
            }
        }
        
        int totalGroups = 2 * (n - rowMasks.size());
        
        int leftMask = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);   
        int rightMask = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);  
        int middleMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7); 
        
        for (int mask : rowMasks.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;
            
            if (leftFree && rightFree) {
                totalGroups += 2; 
            } else if (leftFree || rightFree || (mask & middleMask) == 0) {
                totalGroups += 1; 
            }
        }
        
        return totalGroups;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna