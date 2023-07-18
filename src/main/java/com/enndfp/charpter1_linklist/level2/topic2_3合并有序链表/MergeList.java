package com.enndfp.charpter1_linklist.level2.topic2_3合并有序链表;

/**
 * 合并有序链表
 *
 * @author Enndfp
 */
public class MergeList {

    /**
     * 虚拟头结点和双指针
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode preHead = new ListNode(-1);
        ListNode preNode = preHead;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                preNode.next = list1;
                list1 = list1.next;
            } else {
                preNode.next = list2;
                list2 = list2.next;
            }
            preNode = preNode.next;
        }
        preNode.next = list1 == null ? list2 : list1;
        return preHead.next;
    }

    /**
     * 合并K个链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (ListNode list : lists) {
            res = mergeTwoLists(res, list);
        }
        return res;
    }

    /**
     * 合并两个链表
     *
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // 1. 找到list2的尾结点
        ListNode list2End = list2;
        while (list2End.next != null) {
            list2End = list2End.next;
        }
        // 2. 寻找[a,b]区间
        ListNode p1 = list1;
        ListNode p2 = list1;
        // 2.1. 找到list1下标为a的前一个结点
        for (int i = 0; i < a - 1; i++) {
            p1 = p1.next;
        }
        // 2.2. 找到list1下标为b的后一个结点
        for (int i = 0; i < b + 1; i++) {
            p2 = p2.next;
        }
        // 3. 合并list1和list2
        list2End.next = p2;
        p1.next = list2;
        return list1;
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
