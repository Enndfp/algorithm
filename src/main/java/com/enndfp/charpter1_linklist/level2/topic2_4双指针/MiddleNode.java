package com.enndfp.charpter1_linklist.level2.topic2_4双指针;

/**
 * 寻找链表中间结点
 *
 * @author Enndfp
 */
public class MiddleNode {

    /**
     * 快慢指针
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
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
