package com.enndfp.charpter1_linklist.level2.topic2_2回文序列;

import java.util.Stack;

/**
 * 判断链表是否是回文
 *
 * @author Enndfp
 */
public class IsPalindromic {

    /**
     * 方法1  全部压栈
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByAllStack(ListNode head) {
        ListNode temp = head;
        Stack<Integer> stack = new Stack();
        //把链表节点的值存放到栈中
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }
        //然后再出栈
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * TODO 方法2  快慢指针+链表反转
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByTwoPoints(ListNode head) {
        return true;
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
