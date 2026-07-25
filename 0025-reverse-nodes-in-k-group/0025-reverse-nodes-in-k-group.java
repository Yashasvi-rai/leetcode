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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prevGroupEnd = dummy;

        while (true) {
            // Find the k-th node from prevGroupEnd
            ListNode kthNode = getKthNode(prevGroupEnd, k);
            if (kthNode == null) {
                break; // Fewer than k nodes remaining, leave them as is
            }

            ListNode nextGroupStart = kthNode.next;
            ListNode groupStart = prevGroupEnd.next;

            // Reverse current k-group
            ListNode prev = nextGroupStart;
            ListNode curr = groupStart;

            while (curr != nextGroupStart) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Connect previous group's end to the new reversed head
            prevGroupEnd.next = kthNode;
            
            // Move prevGroupEnd to the tail of the newly reversed group
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}