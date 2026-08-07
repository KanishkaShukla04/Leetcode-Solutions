import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        long tempT = t;
        int[] tFactors = new int[10];
        int[] primes = {2, 3, 5, 7};
        for (int p : primes) {
            while (tempT % p == 0) {
                tFactors[p]++;
                tempT /= p;
            }
        }
        if (tempT > 1) return "-1"; 

        int n = num.length();
        int[] currentFactors = new int[10];
        int firstZero = -1;

        for (int i = 0; i < n; i++) {
            int digit = num.charAt(i) - '0';
            if (digit == 0) {
                if (firstZero == -1) firstZero = i;
                break;
            }
            addFactors(currentFactors, digit, 1);
        }

        if (firstZero == -1 && isSatisfied(currentFactors, tFactors)) {
            return num;
        }

        int limit = (firstZero != -1) ? firstZero : n - 1;
        for (int i = limit; i >= 0; i--) {
            int currentDigit = num.charAt(i) - '0';
            if (firstZero == -1 || i < firstZero) {
                addFactors(currentFactors, currentDigit, -1);
            }

            for (int d = currentDigit + 1; d <= 9; d++) {
                addFactors(currentFactors, d, 1);
                int remainingLength = n - 1 - i;
                
                if (canForm(currentFactors, tFactors, remainingLength)) {
                    StringBuilder sb = new StringBuilder(num.substring(0, i));
                    sb.append(d);
                    fillSuffix(sb, currentFactors, tFactors, remainingLength);
                    return sb.toString();
                }
                addFactors(currentFactors, d, -1); 
            }
        }

        int requiredLength = n + 1;
        while (true) {
            int[] emptyFactors = new int[10];
            if (canForm(emptyFactors, tFactors, requiredLength)) {
                StringBuilder sb = new StringBuilder();
                fillSuffix(sb, emptyFactors, tFactors, requiredLength);
                return sb.toString();
            }
            requiredLength++;
        }
    }

    private void addFactors(int[] counts, int digit, int delta) {
        if (digit == 2 || digit == 6 || digit == 8) counts[2] += (digit == 2 ? 1 : (digit == 6 ? 1 : 3)) * delta;
        if (digit == 3 || digit == 6 || digit == 9) counts[3] += (digit == 3 ? 1 : (digit == 6 ? 1 : 2)) * delta;
        if (digit == 4) counts[2] += 2 * delta;
        if (digit == 5) counts[5] += delta;
        if (digit == 7) counts[7] += delta;
    }

    private boolean isSatisfied(int[] current, int[] target) {
        return current[2] >= target[2] && current[3] >= target[3] && current[5] >= target[5] && current[7] >= target[7];
    }

    private boolean canForm(int[] current, int[] target, int maxDigits) {
        int req2 = Math.max(0, target[2] - current[2]);
        int req3 = Math.max(0, target[3] - current[3]);
        int req5 = Math.max(0, target[5] - current[5]);
        int req7 = Math.max(0, target[7] - current[7]);

        int minDigits = req5 + req7;
        int bestRemaining = Integer.MAX_VALUE;
        
        for (int n9 = 0; n9 <= (req3 + 1) / 2; n9++) {
            int rem3After9 = Math.max(0, req3 - 2 * n9);
            for (int n8 = 0; n8 <= (req2 + 2) / 3; n8++) {
                int rem2After8 = Math.max(0, req2 - 3 * n8);
                
                int n6 = Math.min(rem2After8, rem3After9);
                int final2 = rem2After8 - n6;
                int final3 = rem3After9 - n6;
                
                int n4 = final2 / 2;
                int n2 = final2 % 2;
                int n3 = final3;
                
                int totalMixedDigits = n9 + n8 + n6 + n4 + n2 + n3;
                bestRemaining = Math.min(bestRemaining, totalMixedDigits);
            }
        }
        return (minDigits + bestRemaining) <= maxDigits;
    }

    private void fillSuffix(StringBuilder sb, int[] current, int[] target, int length) {
        int req2 = Math.max(0, target[2] - current[2]);
        int req3 = Math.max(0, target[3] - current[3]);
        int req5 = Math.max(0, target[5] - current[5]);
        int req7 = Math.max(0, target[7] - current[7]);

        // We will collect the optimal digits directly into a list
        List<Integer> bestDigits = null;

        // Exhaustively trace combinations to find the absolute minimum sorted digit list
        for (int n9 = 0; n9 <= (req3 + 1) / 2; n9++) {
            int rem3 = Math.max(0, req3 - 2 * n9);
            for (int n8 = 0; n8 <= (req2 + 2) / 3; n8++) {
                int rem2 = Math.max(0, req2 - 3 * n8);
                
                int n6 = Math.min(rem2, rem3);
                int f2 = rem2 - n6;
                int f3 = rem3 - n6;
                
                int n4 = f2 / 2;
                int n2 = f2 % 2;
                int n3 = f3;
                
                int total = req5 + req7 + n9 + n8 + n6 + n4 + n2 + n3;
                if (total <= length) {
                    List<Integer> currentDigits = new ArrayList<>();
                    for (int k = 0; k < req7; k++) currentDigits.add(7);
                    for (int k = 0; k < req5; k++) currentDigits.add(5);
                    for (int k = 0; k < n9; k++) currentDigits.add(9);
                    for (int k = 0; k < n8; k++) currentDigits.add(8);
                    for (int k = 0; k < n6; k++) currentDigits.add(6);
                    for (int k = 0; k < n4; k++) currentDigits.add(4);
                    for (int k = 0; k < n3; k++) currentDigits.add(3);
                    for (int k = 0; k < n2; k++) currentDigits.add(2);
                    
                    // Pad leftover spaces with 1s
                    while (currentDigits.size() < length) {
                        currentDigits.add(1);
                    }
                    
                    Collections.sort(currentDigits);
                    
                    if (bestDigits == null || isSmallerList(currentDigits, bestDigits)) {
                        bestDigits = currentDigits;
                    }
                }
            }
        }

        for (int d : bestDigits) {
            sb.append(d);
        }
    }

    private boolean isSmallerList(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return false;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna