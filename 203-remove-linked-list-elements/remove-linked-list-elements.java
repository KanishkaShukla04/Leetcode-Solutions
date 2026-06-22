class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0), curr = dummy;
    dummy.next = head;
    while (curr.next != null) {
        if (curr.next.val == val) {
            curr.next = curr.next.next;
        } else {
            curr = curr.next; // Fixed the infinite loop typo here
        }
    }
    return dummy.next;
    }
}