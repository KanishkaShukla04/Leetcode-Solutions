class Solution {
    public int evalRPN(String[] tokens) {
         int[] stack = new int[tokens.length];
        int top = 0;       
        for (String t : tokens) {
            switch (t) {
                case "+" -> {
                    stack[top - 2] += stack[top - 1];
                    top--;
                }
                case "-" -> {
                    stack[top - 2] -= stack[top - 1];
                    top--;
                }
                case "*" -> {
                    stack[top - 2] *= stack[top - 1];
                    top--;
                }
                case "/" -> {
                    stack[top - 2] /= stack[top - 1];
                    top--;
                }
                default -> stack[top++] = Integer.parseInt(t);
            }
        }
        return stack[0];  
    }
}