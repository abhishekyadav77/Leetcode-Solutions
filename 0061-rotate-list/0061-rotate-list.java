/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
         // Empty list or only one node
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Find length
        int n = 0;
        ListNode curr = head;

        while (curr != null) {
            n++;
            curr = curr.next;
        }

        // Step 2: Reduce unnecessary rotations
        k = k % n;

        if (k == 0) {
            return head;
        }

        // Step 3: Find node just before new head
        curr = head;

        for (int i = 1; i < n - k; i++) {
            curr = curr.next;
        }

        // Step 4: Save new head
        ListNode newHead = curr.next;

        // Step 5: Break the list
        curr.next = null;

        // Step 6: Go to last node
        curr = newHead;

        while (curr.next != null) {
            curr = curr.next;
        }

        // Step 7: Connect last node to old head
        curr.next = head;

        return newHead;
    }
}