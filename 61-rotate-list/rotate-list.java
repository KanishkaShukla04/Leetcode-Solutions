class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
    
    int length = 1;
    ListNode tail = head;
    while (tail.next != null) {
        tail = tail.next;
        length++;
    }
    
    k = k % length;
    if (k == 0) return head;
    
    tail.next = head; 
    
    int stepsToNewTail = length - k;
    for (int i = 0; i < stepsToNewTail; i++) {
        tail = tail.next;
    }
    
    head = tail.next; 
    tail.next = null; 
    
    return head;
    }
}