package com.enndfp.charpter2_reverselist.level1;

/**
 * 反转链表
 *
 * @author Enndfp
 */
public class ReverseList {

    /**
     * 方法1：带虚拟头结点
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = dummyHead.next;
            dummyHead.next = cur;
            cur = next;
        }

        return dummyHead.next;
    }

    /**
     * 方法2：不带虚拟头结点
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
