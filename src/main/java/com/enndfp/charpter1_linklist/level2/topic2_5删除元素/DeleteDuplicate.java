package com.enndfp.charpter1_linklist.level2.topic2_5删除元素;

/**
 * 删除链表中的重复元素,保留一个
 *
 * @author Enndfp
 */
public class DeleteDuplicate {

    /**
     * 删除链表中的重复元素,保留一个
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
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
