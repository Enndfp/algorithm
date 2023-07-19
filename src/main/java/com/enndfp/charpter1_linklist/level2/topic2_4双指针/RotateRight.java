package com.enndfp.charpter1_linklist.level2.topic2_4双指针;

/**
 * 旋转链表
 *
 * @author Enndfp
 */
public class RotateRight {

    /**
     * 旋转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        // 1. 判断链表是否为空或k是否为0
        if (head == null || k == 0) {
            return head;
        }
        // 2. 定义所需变量结点
        ListNode temp = head;
        ListNode slow = head;
        ListNode fast = head;
        int len = 0;
        // 3. 求出链表长度
        while (head != null) {
            head = head.next;
            len++;
        }
        // 4. k如果是链表长度的倍数则相当于没移，直接返回
        if (k % len == 0) {
            return temp;
        }
        // 5. 先让快指针走k步
        while ((k % len) > 0) {
            fast = fast.next;
            k--;
        }
        // 6. 快慢指针一起移动，知道快指针移到最后一个结点
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 7. 拼接链表
        ListNode res = slow.next;
        slow.next = null;
        fast.next = temp;
        // 8. 返回
        return res;
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
