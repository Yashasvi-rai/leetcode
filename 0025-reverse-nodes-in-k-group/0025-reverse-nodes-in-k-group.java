class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (true) {
            ListNode end = prev;

            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }

            if (end == null) {
                break;
            }

            ListNode start = prev.next;
            ListNode next = end.next;

            end.next = null;
            prev.next = reverse(start);
            start.next = next;

            prev = start;
        }

        return dummy.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }
}