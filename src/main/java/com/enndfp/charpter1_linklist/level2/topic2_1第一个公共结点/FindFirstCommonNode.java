package com.enndfp.charpter1_linklist.level2.topic2_1第一个公共结点;

import java.util.*;

/**
 * @author Enndfp
 */
public class FindFirstCommonNode {

    /**
     * 方法1  通过hash辅助查找
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode FindFirstCommonNodeByMap(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Map<ListNode, Integer> map = new HashMap<>();
        while (headA != null) {
            map.put(headA, null);
            headA = headA.next;
        }
        while (headB != null) {
            if (map.containsKey(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /**
     * 方法2  通过集合辅助查找
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode FindFirstCommonNodeBySet(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /**
     * 方法3  通过栈来辅助查找
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode findFirstCommonNodeByStack(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();

        while (headA != null) {
            stackA.push(headA);
            headA = headA.next;
        }
        while (headB != null) {
            stackB.push(headB);
            headB = headB.next;
        }

        ListNode pNode = null;
        while (stackA.size() > 0 && stackB.size() > 0) {
            if (stackA.peek() == stackB.peek()) {
                pNode = stackA.pop();
                stackB.pop();
            } else {
                break;
            }
        }
        return pNode;
    }

    /**
     * 方法4  通过双指针来辅助查找
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode ptrA = headA;
        ListNode ptrB = headB;

        while (ptrA != ptrB) {
            ptrA = ptrA == null ? headB : ptrA.next;
            ptrB = ptrB == null ? headA : ptrB.next;
        }

        return ptrA;
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
