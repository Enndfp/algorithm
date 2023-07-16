package com.enndfp.charpter1_linklist.level1;

/**
 * @author Enndfp
 */
public class BasicLinkList {

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
            next = null;
        }
    }

    public static void main(String[] args) {
        // 头部添加节点1
        Node head = new Node(1);
        System.out.println("头部添加节点1：" + printLinkedList(head));
        System.out.println("链表长度为：" + getLength(head));

        // 尾部添加节点2
        Node node = new Node(2);
        head = insertNode(head, node, 2);
        System.out.println("尾部添加节点2：" + printLinkedList(head));
        System.out.println("链表长度为：" + getLength(head));

        // 中间添加节点3
        node = new Node(3);
        head = insertNode(head, node, 2);
        System.out.println("中间添加节点3：" + printLinkedList(head));
        System.out.println("链表长度为：" + getLength(head));

        // 删除中间节点3
        head = BasicLinkList.deleteNode(head, 2);
        System.out.println("删除中间节点3：" + printLinkedList(head));
        System.out.println("链表长度为：" + getLength(head));

        // 删除头部节点1
        head = BasicLinkList.deleteNode(head, 1);
        System.out.println("删除头部节点1：" + printLinkedList(head));
        System.out.println("链表长度为：" + getLength(head));
    }

    /**
     * 获取链表长度
     *
     * @param head
     * @return
     */
    public static int getLength(Node head) {
        int length = 0;
        Node node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    /**
     * 向链表中插入结点
     *
     * @param head       链表头结点
     * @param nodeInsert 待插入的结点
     * @param position   插入位置
     * @return 新链表的头结点
     */
    public static Node insertNode(Node head, Node nodeInsert, int position) {
        // 1.先判空，否则报空指针异常
        if (head == null) {
            return nodeInsert;
        }
        // 2.越界判断
        int size = getLength(head);
        if (position < 1 || position > size + 1) {
            System.out.println("插入位置越界");
            return head;
        }

        // 在链表开头插入
        if (position == 1) {
            nodeInsert.next = head;
            return nodeInsert;
        }

        // 遍历寻找待插入结点的前一个结点
        Node preNode = head;
        int count = 1;
        while (count < position - 1) {
            count++;
            preNode = preNode.next;
        }

        // 插入结点
        nodeInsert.next = preNode.next;
        preNode.next = nodeInsert;

        return head;
    }

    /**
     * 删除链表结点
     *
     * @param head     待删链表头结点
     * @param position 删除结点位置
     * @return 新链表头结点
     */
    public static Node deleteNode(Node head, int position) {
        // 1.先判空
        if (head == null) {
            return null;
        }
        // 2.判断位置参数
        int size = getLength(head);
        if (position > size || position < 1) {
            System.out.println("删除位置有误");
            return head;
        }

        // 头删法
        if (position == 1) {
            return head.next;
        }

        // 遍历寻找待删除结点的前一个结点
        Node preNode = head;
        int count = 1;
        while (count < position - 1) {
            count++;
            preNode = preNode.next;
        }

        // 删除结点
        preNode.next = preNode.next.next;
        return head;
    }

    /**
     * 输出链表
     *
     * @param head
     * @return
     */
    public static String printLinkedList(Node head) {
        StringBuffer sb = new StringBuffer();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
            if (head != null) {
                sb.append("->");
            }
        }
        return sb.toString();
    }


}
