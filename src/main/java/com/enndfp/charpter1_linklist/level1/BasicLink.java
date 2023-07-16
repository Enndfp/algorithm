package com.enndfp.charpter1_linklist.level1;

/**
 * @author Enndfp
 */
public class BasicLink {

    /**
     * 构造链表
     */
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            next = null;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        Node head = initLinkedList(arr);
        System.out.println(head);
    }

    /**
     * 初始化链表
     * @param arr
     * @return
     */
    private static Node initLinkedList(int[] arr) {
        Node head = null;   // 头结点
        Node cur = null;    // 当前结点

        for (int i = 0; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            newNode.next = null;
            if (i == 0) {
                head = newNode;
                cur = newNode;
            } else {
                cur.next = newNode;
                cur = newNode;
            }
        }
        return head;
    }


}
