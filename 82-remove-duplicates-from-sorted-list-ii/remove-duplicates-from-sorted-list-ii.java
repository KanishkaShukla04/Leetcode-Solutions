class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    
    while (prev.next != null && prev.next.next != null) {
        if (prev.next.val == prev.next.next.val) {
            int duplicateVal = prev.next.val;
            while (prev.next != null && prev.next.val == duplicateVal) {
                prev.next = prev.next.next;
            }
        } else {
            prev = prev.next;
        }
    }
    return dummy.next;   
    }
}