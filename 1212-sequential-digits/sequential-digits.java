import java.util.*;
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        // Loop through all possible lengths of a number (from 2 digits up to 9 digits)
        for (int length = 2; length <= 9; length++) {
            // Slide a window of current 'length' across the master digit string
            for (int start = 0; start <= 9 - length; start++) {
                String sub = digits.substring(start, start + length);
                int num = Integer.parseInt(sub);
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        return result;
    }
}
