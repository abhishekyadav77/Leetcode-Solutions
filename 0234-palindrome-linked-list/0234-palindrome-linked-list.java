class Solution {
    public boolean isPalindrome(ListNode head) {

        // Step 1 Find middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // If length is odd  skip middle element
        if (fast != null) {
            slow = slow.next;
        }

     // Step2 Reverse second half
        ListNode second = reverseList(slow);

            // First half
        ListNode first = head;

  // Step 3 Compare
        while (second != null) {

            if (first.val != second.val) {
                return false;
            }

            first = first.next;
            second = second.next;
        }

        return true;
    }

    private ListNode reverseList(ListNode head) {
  ListNode prev = null;
        ListNode curr = head;

        while (curr!= null) {

            ListNode next = curr.next;

            curr.next= prev;

            prev =curr;
            curr= next;
        }

        return prev;
    }
}