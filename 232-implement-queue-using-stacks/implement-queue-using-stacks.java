import java.util.Stack;

class MyQueue {
    private final Stack<Integer> in = new Stack<>();
    private final Stack<Integer> out = new Stack<>();
    public void push(int x) {
        in.push(x);
    }
    public int pop() {
        peek();
        return out.pop();
    }
    public int peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
}
