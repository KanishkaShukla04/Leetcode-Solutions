class Solution {
    public ListNode partition(ListNode head, int x) {
         ListNode lessHead = new ListNode(0), less = lessHead;
    ListNode greaterHead = new ListNode(0), greater = greaterHead;
    
    while (head != null) {
        if (head.val < x) {
            less.next = head;
            less = less.next;
        } else {
            greater.next = head;
            greater = greater.next;
        }
        head = head.next;
    }
    
    greater.next = null; // Cut off any potential cycles
    less.next = greaterHead.next; // Stitch the two lists together
    return lessHead.next;
        
    }
}