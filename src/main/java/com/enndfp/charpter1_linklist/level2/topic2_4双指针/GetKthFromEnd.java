package com.enndfp.charpter1_linklist.level2.topic2_4双指针;

/**
 * 寻找倒数第K个元素
 *
 * @author Enndfp
 */
public class GetKthFromEnd {

    /**
     * 寻找倒数第K个元素
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head, fast = head;
        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
