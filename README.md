# 算法训练（algorithm）

![](https://img.enndfp.cn/algorithm.png)

# 算法通关村第一关---链表经典问题笔记

### 1.剑指 Offer 52. 两个链表的第一个公共节点

**题目地址：[LeetCode](https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/)**

![image-20230717225737253](https://img.enndfp.cn/image-20230717225737253.png)

**注意：**

- 如果两个链表没有交点，返回`null`
- 在返回结果后，两个链表仍须保持原有的结构
- 可假定整个链表结构中没有循环
- 程序尽量满足O(n)时间复杂度，且仅用O(1)内存

**解题思路：**

只有当链表`headA`和`headB`都不为空时，两个链表才可能相交。因此首先判断链表`headA`和`headB`是否为空，如果其中至少有一个链表为空，则两个链表一定不相交，返回`null`

当链表`headA`和`headB`都不为空时，创建两个指针`pA`和`pB`,初始时分别指向两个链表的头节点`headA`和`headB`,然后将两个指针依次遍历两个链表的每个节点。具体做法如下：

- 每步操作需要同时更新指针`pA`和`pB`
- 如果指针`pA`不为空，则将指针`pA`移到下一个节点；如果指针`pB`不为空，则将指针`pB`移到下一个节点
- 如果指针`pA`为空，则将指针`pA`移到链表`headB`的头节点；如果指针`pB`为空，则将指针`pB`移到链表`headA`的头节点
- 当指针`pA`和`pB`指向同一个节点或者都为空时，返回它们指向的节点或者`null`

```java
/**
     * 通过双指针来辅助查找
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }

        return p1;
    }
```

### 2.LeetCode 234. 回文链表

**题目地址：[LeetCode](https://leetcode.cn/problems/palindrome-linked-list/)**

![image-20230718141944439](https://img.enndfp.cn/image-20230718141944439.png)

**解题思路：**

1. 创建一个空的整数栈 `stack` 用于暂存链表节点的值。
2. 创建一个指向输入链表 `head` 的指针 `temp`，用于遍历整个链表。
3. 遍历链表，将每个节点的值压入栈中：
   - 使用 while 循环，遍历链表直到 `temp` 指向最后一个节点为止。
   - 在循环中，将当前节点的值 `temp.val` 压入栈 `stack` 中，并将 `temp` 指针指向下一个节点，继续遍历。
4. 再次遍历链表，同时从栈中弹出元素并与链表节点的值进行比较：
   - 使用另一个 while 循环，遍历链表直到 `head` 为 `null`，同时检查栈是否为空。
   - 在循环中，从栈中弹出栈顶元素，将其与当前链表节点的值进行比较。若不相等，说明链表不是回文的，返回 `false`。
   - 如果比较成功，将 `head` 指针移动到下一个节点，继续进行下一轮比较。
5. 如果第二个 while 循环能够顺利执行完毕（没有提前返回 `false`），说明链表中的所有元素都是回文的，返回 `true`。

总结：该解题方法首先利用栈的后进先出特性将链表节点的值压入栈中，然后通过再次遍历链表与栈中元素的比较，判断链表是否为回文结构。

```java 
/**
     * 全部压栈
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
```

### 3.LeetCode 21. 合并两个有序链表

**题目地址：[LeetCode](https://leetcode.cn/problems/merge-two-sorted-lists/)**

![image-20230718143741579](https://img.enndfp.cn/image-20230718143741579.png)

**解题思路：**

1. 创建一个虚拟头节点 `preHead` 作为合并后链表的起始点，并创建一个当前节点指针 `preNode` 初始化指向 `preHead`。
2. 使用 while 循环，遍历两个有序链表 `list1` 和 `list2`，直到其中一个链表为空。
3. 在循环中，比较 `list1` 和 `list2` 当前节点的值，将较小的节点接入合并链表，并将相应的链表指针向后移动。
4. 更新 `preNode` 指向新接入的节点，使其指向合并链表的最后一个节点。
5. 循环结束后，其中一个链表已经遍历完毕，可能还有一个链表中的节点未处理完毕。此时，直接将剩余未处理的链表部分接入合并链表的末尾。
6. 返回 `preHead.next`，即合并后的有序链表的起始节点。

```java
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
```

#### 变型

```java
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
```

### 4.LeetCode 1669. 合并两个链表

**题目地址：[LeetCode](https://leetcode.cn/problems/merge-in-between-linked-lists/)**

![image-20230718162856232](https://img.enndfp.cn/image-20230718162856232.png)

**解题思路：**

1. 找到`list2`的尾结点。
   - 将`list2`的头节点赋值给`list2End`。
   - 使用循环遍历`list2`直到最后一个节点，同时更新`list2End`的值。
2. 寻找需要替换的区间`[a, b]`在`list1`中的位置。
   - 初始化两个指针`p1`和`p2`为`list1`的头节点。
   - 使用循环将`p1`移动到下标为`a - 1`的位置，将`p2`移动到下标为`b + 1`的位置。
3. 合并`list1`和`list2`。
   - 将`list2`接在`list1`的前一个节点`p1`之后，即`p1.next = list2`。
   - 将`list2End`的后继节点接在`list1`的后一个节点`p2`之前，即`list2End.next = p2`。
4. 返回合并后的链表`list1`。

```java
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
```

### 5.LeetCode 876. 链表的中间结点

**题目地址：[LeetCode](https://leetcode.cn/problems/middle-of-the-linked-list/)**

![image-20230719084432412](https://img.enndfp.cn/image-20230719084432412.png)

**解题思路：**

1. 初始化两个指针 `slow` 和 `fast`，它们都指向链表的头节点 `head`。
2. 在循环中，同时移动 `slow` 和 `fast` 指针。每次迭代，`slow` 指针向前移动一步，而 `fast` 指针向前移动两步。
3. 循环的条件是 `fast` 不为 `null` 并且 `fast.next` 不为 `null`。这样做的目的是确保在每次迭代时，`fast` 指针都至少能够移动两步，避免出现空指针异常。
4. 当 `fast` 到达链表末尾时，即 `fast` 为 `null` 或者 `fast.next` 为 `null`，循环结束。
5. 最终返回 `slow` 指针的位置，即链表的中间节点。由于 `fast` 指针每次移动两步，而 `slow` 指针每次移动一步，所以当循环结束时，`slow` 指针正好指向链表的中间节点。

这种快慢指针法的原理在于，快指针的速度是慢指针的两倍，所以当快指针到达链表末尾时，慢指针刚好在链表的中间位置。

```java
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
```

### 6.寻找倒数第K个元素

输入一个链表，输出该链表中倒数第k个节点。本题从1开始计数，即链表的尾节点是倒数第1个节点。
示例
给定一个链表：1->2->3->4->5，和k=2.
返回链表4->5.

**解题思路：**

1. 先将`fast`向后遍历到第`k+1`个节点，`slow`仍然指向链表的第一个节点，此时指针`fast`与`slow`二者之间刚好间隔`k`个节点。
2. 之后两个指针同步向后走，当`fas`t走到链表的尾部`空节点`时，`slow`指针刚好指向链表的倒数第`k`个节点。

```java
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
```

### 7.LeetCode 61. 旋转链表

**题目地址：[LeetCode](https://leetcode.cn/problems/rotate-list/)**

![](https://img.enndfp.cn/image-20230719140735178.png)

![image-20230719141104100](https://img.enndfp.cn/image-20230719141104100.png)

**解题思路：**

1. 首先检查输入的链表是否为空（`head == null`）或者`k`是否为`0`。如果是其中之一，直接返回原链表，因为旋转`0`次或者对空链表旋转没有任何变化。
2. 定义三个指针变量：`temp`、`slow`、`fast`，它们都初始化为链表的头节点`head`。其中，`temp`用于最后返回结果，`slow`和`fast`用于找到旋转后的新头节点。
3. 使用一个循环遍历链表，计算链表的长度`len`。此时`head`指针被移动到了链表的尾部，用于后续的操作。
4. 如果`k`是链表长度`len`的倍数，说明旋转后的链表和原链表相同，因此直接返回原链表`head`。
5. 如果`k`不是链表长度`len`的倍数，则需要进行旋转操作。首先，通过一个循环将`fast`指针向后移动`k`次，即找到旋转后的新头节点的前一个节点。
6. 接下来，使用两个指针`slow`和`fast`一起移动，直到`fast`指针指向链表的最后一个节点。此时`slow`指针指向旋转后的新头节点的前一个节点。
7. 将`slow`的下一个节点作为新的头节点，将`fast`的`next`指针指向原链表的头节点，实现链表的旋转。
8. 最后，返回新的头节点，即`res`。

```java
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
```

### 8.LeetCode 203. 移除链表元素

**题目地址：[LeetCode](https://leetcode.cn/problems/remove-linked-list-elements/)**

![image-20230720085958030](https://img.enndfp.cn/image-20230720085958030.png)

**解题思路：**

1. 创建一个虚拟头节点 `dummyHead`，将它的 `next` 指针指向原链表的头节点 `head`。
2. 初始化一个指针 `cur`，指向虚拟头节点 `dummyHead`。
3. 遍历链表，循环条件是 `cur.next` 不为空，即 `cur` 不是链表的最后一个节点。
4. 在循环中，检查 `cur.next` 节点的值是否等于给定的值 `val`。如果相等，说明需要删除该节点，直接将 `cur.next` 指针跳过当前节点，指向当前节点的下一个节点（即删除当前节点）。
5. 如果 `cur.next` 节点的值不等于给定的值 `val`，则说明当前节点不需要删除，将 `cur` 指针向后移动一个节点，继续下一轮循环。
6. 循环结束后，链表中所有值为 `val` 的节点都被删除了。
7. 返回处理后的链表头节点，即为 `dummyHead.next`，作为最终结果。

```java
/**
     * 删除特定值元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;

        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
```

### 9.LeetCode 19. 删除链表的倒数第 N 个结点

**题目地址：[LeetCode](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)**

![image-20230720093117957](https://img.enndfp.cn/image-20230720093117957.png)

**解题思路：**

1. 创建一个虚拟头节点 `dummyHead`，将其 `next` 指针指向链表的头节点 `head`。这样做是为了方便处理头节点的删除情况。
2. 初始化两个指针 `slow` 和 `fast`，`slow`指向虚拟头节点 `dummyHead`、`fast`指向头结点`head`。
3. 移动 `fast` 指针，使其先走 N 步。这样，`fast` 和 `slow` 之间就相隔 N 个节点。
4. 然后，同时移动 `fast` 和 `slow` 指针，直到 `fast` 指针指向链表末尾（即 `fast` 为 `null`）。
5. 此时，`slow` 指针指向倒数第 N+1 个节点。
6. 修改 `slow.next` 指针，使其指向倒数第 N+1 个节点的下一个节点，即跳过倒数第 N 个节点，从而实现删除倒数第 N 个节点。
7. 返回虚拟头节点的 `next` 指针，即为最终的链表头节点，表示完成删除操作后的链表。

```java
/**
     * 删除链表的倒数第 N 个结点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode slow = dummyHead;
        ListNode fast = head;

        while (n > 0) {
            fast = fast.next;
            n--;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        
        return dummyHead.next;
    }
```

### 10.LeetCode 83. 删除排序链表中的重复元素

**题目地址：[LeetCode](https://leetcode.cn/problems/remove-duplicates-from-sorted-list/)**

![image-20230721090136913](https://img.enndfp.cn/image-20230721090136913.png)

**解题思路：**

1. 首先，检查输入的链表头节点是否为null，如果是空链表，则直接返回该头节点null。
2. 如果链表不为空，就从头节点开始遍历链表。使用一个指针cur来代表当前遍历到的节点，开始时指向头节点head。
3. 在遍历过程中，对于每个节点cur，检查其后继节点cur.next是否存在，即cur是否为链表中的最后一个节点。如果cur.next存在，继续执行下面的步骤，否则表示已经遍历到了链表末尾，可以结束遍历。
4. 检查当前节点cur的值cur.val和它的后继节点cur.next的值是否相等。如果相等，说明该节点是重复元素，因为链表是有序的，所以连续重复的元素会相邻。为了删除重复元素，只需要将当前节点cur的next指针指向其后继节点cur.next的next节点，即跳过一个重复元素，相当于删除了重复元素。这里的操作是 `cur.next = cur.next.next;`
5. 如果当前节点cur的值cur.val和后继节点cur.next的值不相等，表示当前节点不是重复元素，直接将指针cur移动到下一个节点，即 `cur = cur.next;`，继续遍历。
6. 重复执行步骤3至步骤5，直到遍历到链表末尾为止。
7. 最后，返回删除重复元素后的链表的头节点head，即可得到链表中没有重复元素的链表。

```java
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
```

### 11.LeetCode 82. 删除排序链表中的重复元素 II

**题目地址：[LeetCode](https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/)**

![image-20230721092028149](https://img.enndfp.cn/image-20230721092028149.png)

**解题思路：**

1. 首先，检查输入的链表头节点是否为`null`，如果是空链表，则直接返回该头节点`null`。

2. 创建一个虚拟头节点(`dummyHead`)。将虚拟头节点的`next`指针指向原链表的头节点`head`，以便处理链表开头的重复元素情况。

3. 使用指针`cur`来代表当前遍历到的节点，开始时指向虚拟头节点`dummyHead`。

4. 在遍历过程中，对于每个节点`cur`，检查其后继节点`cur.next`和后继节点的后继节点`cur.next.next`是否存在，即cur是否至少为链表中的倒数第二个节点。如果`cur.next`和`cur.next.next`都存在，继续执行下面的步骤，否则表示已经遍历到了链表倒数第二个节点或更短的链表，可以结束遍历。

5. 检查当前节点`cur`的值`cur.next.val`和它的后继节点`cur.next.next.val`是否相等。如果相等，说明链表中存在重复元素。为了删除所有重复元素，需要进入一个循环，将指针`cur`的`next`指向不等于`x`的下一个节点，直到`cur`的`next`指向的节点值不等于`x`。这里的操作是：

   ```java
   int x = cur.next.val;
   while (cur.next != null && cur.next.val == x) {
       cur.next = cur.next.next;
   }
   ```

6. 如果当前节点`cur`的值`cur.next.val`和后继节点`cur.next.next.val`不相等，表示当前节点`cur`的后继节点`cur.next`是唯一的，不是重复元素，直接将指针`cur`移动到下一个节点，即 `cur = cur.next;`，继续遍历。

7. 重复执行步骤4至步骤6，直到遍历到链表倒数第二个节点为止。

8. 最后，返回虚拟头节点`dummyHead`的`next`指针所指向的链表头节点，即可得到删除所有重复元素的链表。

```java
/**
     * 删除链表中的重复元素,全都不要
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
```

# 算法通关村第二关---链表反转

### 1.LeetCode 206. 反转链表

**题目地址：[LeetCode](https://leetcode.cn/problems/reverse-linked-list/)**

![image-20230722091010803](https://img.enndfp.cn/image-20230722091010803.png)

**1.解题思路：**

1. 创建虚拟头结点：首先，创建一个名为`dummyHead`的虚拟头结点，它将充当反转后链表的新头结点。虚拟头结点的值设置为-1，但它不包含任何实际数据。
2. 遍历原链表：然后，代码使用`cur`指针来遍历原始链表`head`。
3. 反转操作：在每次遍历中，将`cur`指向的节点从原链表中取下，并将其插入到虚拟头结点之后，成为反转后链表的新头结点。
4. 更新指针：进行插入操作时，先将`cur.next`指针暂存为`next`，然后将`cur.next`指向虚拟头结点的下一个节点，再将虚拟头结点的`next`指针指向`cur`，最后将`cur`指向暂存的`next`，继续遍历原链表。
5. 完成反转：当遍历完原链表后，虚拟头结点的`next`指针指向的节点就是反转后链表的新头结点。
6. 返回结果：最后，返回反转后链表的头结点，即`dummyHead.next`。

![image-20230722094932065](https://img.enndfp.cn/image-20230722094932065.png)

```java
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
```

**2.解题思路：**

1. 初始化指针：首先，创建两个指针`pre`和`cur`，分别用于记录前一个节点和当前节点。开始时，`pre`为`null`，`cur`指向原链表的头结点`head`。
2. 遍历原链表：通过循环遍历原链表，从头结点一直遍历到末尾节点。在每次循环中，执行反转操作。
3. 反转操作：在每次循环中，首先将当前节点`cur`的下一个节点暂存为`next`，然后将`cur.next`指向`pre`，将当前节点指向的下一个节点指向前一个节点，实现反转操作。
4. 更新指针：完成反转操作后，需要将指针向前移动。将`pre`指向当前节点`cur`，将`cur`指向暂存的下一个节点`next`，继续遍历原链表。
5. 完成反转：当遍历完原链表后，`pre`指针所指的节点就是反转后链表的头结点。
6. 返回结果：最后，返回反转后链表的头结点，即`pre`。

![image-20230722095006376](https://img.enndfp.cn/image-20230722095006376.png)

```java
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
```

### 2.LeetCode 92. 反转链表 II

**题目地址：[LeetCode](https://leetcode.cn/problems/reverse-linked-list-ii/)**

![image-20230724084751031](https://img.enndfp.cn/image-20230724084751031.png)

**解题思路：**

![image-20230724095608626](https://img.enndfp.cn/image-20230724095608626.png)

![image-20230724095641164](https://img.enndfp.cn/image-20230724095641164.png)

```java
/**
     * 带虚拟头结点指定区间反转链表
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        
        ListNode cur = pre.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        
        return dummyHead.next;
    }
```

# 算法通关村第三关---数组

### 1.LeetCode 896. 单调数列

**题目地址：[LeetCode](https://leetcode.cn/problems/monotonic-array/)**

![image-20230728214502967](https://img.enndfp.cn/image-20230728214502967.png)

![image-20230728214520228](https://img.enndfp.cn/image-20230728214520228.png)

```java
/**
     * 一次遍历
     *
     * @param nums
     * @return
     */
    public boolean isMonotonic(int[] nums) {
        boolean inc = true, dec = true;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                inc = false;
            }
            if (nums[i] < nums[i + 1]) {
                dec = false;
            }
        }
        return inc || dec;
    }
```

### 2.LeetCode 35. 搜索插入位置

**题目地址：[LeetCode](https://leetcode.cn/problems/search-insert-position/)**

![image-20230728214719966](https://img.enndfp.cn/image-20230728214719966.png)

![image-20230728214728642](https://img.enndfp.cn/image-20230728214728642.png)

**解题思路：**

![image-20230728215431494](https://img.enndfp.cn/image-20230728215431494.png)

![image-20230728215444129](https://img.enndfp.cn/image-20230728215444129.png)

![image-20230728215454337](https://img.enndfp.cn/image-20230728215454337.png)

![image-20230728215517831](https://img.enndfp.cn/image-20230728215517831.png)

![image-20230728215527485](https://img.enndfp.cn/image-20230728215527485.png)

```java
/**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
```

### 3.LeetCode 88. 合并两个有序数组

**题目地址：[LeetCode](https://leetcode.cn/problems/merge-sorted-array/description/)**

![image-20230728215907437](https://img.enndfp.cn/image-20230728215907437.png)

![image-20230728215916827](https://img.enndfp.cn/image-20230728215916827.png)

```java
/**
     * 逆向双指针
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        for (int index = k - 1, nums1Index = m - 1, nums2Index = n - 1; index >= 0; index--) {
            if (nums1Index < 0) {
                //nums1已经取完，完全取nums2的值即可
                nums1[index] = nums2[nums2Index--];
            } else if (nums2Index < 0) {
                //nums2已经取完，完全取nums1的值即可
                break;
            } else if (nums1[nums1Index] > nums2[nums2Index]) {
                //nums1的元素值大于nums2，取nums1值
                nums1[index] = nums1[nums1Index--];
            } else {
                //nums2的元素值大于nums1，取nums2值
                nums1[index] = nums2[nums2Index--];
            }
        }
    }
```

### 4.LeetCode 27. 移除元素

**题目地址：[LeetCode](https://leetcode.cn/problems/remove-element/)**

![image-20230730213012163](https://img.enndfp.cn/image-20230730213012163.png)

![image-20230730213025857](https://img.enndfp.cn/image-20230730213025857.png)

**解题思路：**

![image-20230730215548078](https://img.enndfp.cn/image-20230730215548078.png)

1. 初始化两个指针：`slow` 和 `fast`。这两个指针都从数组的开头（索引 0）开始。
2. 通过`fast`指针遍历数组，从第一个元素（索引 0）开始，向数组的末尾移动。
3. 对于`fast`指针当前指向的元素，检查它是否等于指定的值`val`。
4. 如果当前元素不等于`val`，说明我们要保留这个元素在修改后的数组中（因为我们要移除所有的`val`）。在这种情况下，将该元素复制到`slow`指针的位置，然后将`slow`指针移动到下一个位置。这一步骤有效地将非`val`元素覆盖到数组中原来`val`的位置。
5. 如果当前元素等于`val`，则直接跳过，不做任何处理，然后将`fast`指针移到下一个元素的位置，`slow`指针保持不动。
6. 重复步骤 4 和 5，直到`fast`指针到达数组的末尾。
7. 此时`slow`指针的值表示修改后的新数组的长度，其中所有的`val`元素已经被移除。
8. 返回`slow`的值，即为修改后的数组中不包含`val`的元素个数。

```java
/**
     * 快慢双指针
     *
     * @param nums 待处理数组
     * @param val  待删元素
     * @return 不重复元素个数
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
```

### 5.LeetCode 26. 删除有序数组中的重复项

**题目地址：[LeetCode](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/)**

![image-20230730215736966](https://img.enndfp.cn/image-20230730215736966.png)

![image-20230730215750409](https://img.enndfp.cn/image-20230730215750409.png)

**解题思路：**

1. 初始化两个指针：`slow` 和 `fast`。其中，`slow` 表示可以放入新元素的位置，初始值为 1（因为索引为 0 的元素不需要管，可以保留在新数组中）。
2. 使用循环来模拟快指针的移动，`fast` 指针从索引 0 开始，向数组的末尾移动。
3. 对于每个 `fast` 指针指向的元素，检查它是否与上一个位置（`slow - 1`索引处）的元素相同。
4. 如果当前元素与上一个元素不相同，说明该元素是新的不重复元素，应该放入新数组中。将该元素复制到 `slow` 指针的位置，然后将 `slow` 指针移动到下一个位置。
5. 如果当前元素与上一个元素相同，则跳过该元素，不将其放入新数组中，继续考察下一个元素。
6. 重复步骤 4 和 5，直到 `fast` 指针遍历完整个数组。
7. 此时 `slow` 指针的值表示新数组的长度，其中包含了不重复的元素。
8. 返回 `slow` 的值作为新数组的长度。

```java
/**
     * 快慢双指针
     *
     * @param nums 待处理数组
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        //slow表示可以放入新元素的位置，索引为0的元素不用管
        int slow = 1;
        //循环起到了快指针的作用
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
```

### 6.LeetCode 905. 按奇偶排序数组

**题目地址：[LeetCode](https://leetcode.cn/problems/sort-array-by-parity/)**

![image-20230801191941649](https://img.enndfp.cn/image-20230801191941649.png)

![image-20230801192024487](https://img.enndfp.cn/image-20230801192024487.png)

**解题思路：**

![image-20230801193619993](https://img.enndfp.cn/image-20230801193619993.png)

1. 创建两个指针 `left` 和 `right`，分别初始化为数组的开头和结尾。
2. 目标是将所有偶数移到数组的左侧，将所有奇数移到数组的右侧。
3. 进入一个 `while` 循环，循环条件是 `left` 小于 `right`。
4. 在循环中：
   - 检查索引 `left` 处的数是否为奇数（`nums[left] % 2 == 1`），以及索引 `right` 处的数是否为偶数（`nums[right] % 2 == 0`）。
   - 如果上述条件为真，则交换索引 `left` 和 `right` 处的数，这样就把偶数移到了左侧，把奇数移到了右侧。
   - 如果索引 `left` 处的数是偶数，则将 `left` 增加1；如果索引 `right` 处的数是奇数，则将 `right` 减少1。这样我们可以继续向数组中心移动，继续交换偶数和奇数。
5. 当 `while` 循环结束时，数组将按照奇偶性进行了分区，偶数位于数组的左侧，奇数位于数组的右侧。
6. 返回排序后的数组。

```java
/**
     * 对撞型双指针
     *
     * @param nums
     * @return
     */
    public int[] sortArrayByParity(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] % 2 > nums[right] % 2) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
            if (nums[left] % 2 == 0) left++;
            if (nums[right] % 2 == 1) right--;
        }
        return nums;
    }
```

### 7.LeetCode 189. 轮转数组

**题目地址：[LeetCode](https://leetcode.cn/problems/rotate-array/)**

![image-20230801194728585](https://img.enndfp.cn/image-20230801194728585.png)

**解题思路：**

![image-20230801200043716](https://img.enndfp.cn/image-20230801200043716.png)

```java
public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }
```

### 8.LeetCode 228. 汇总区间

**题目地址：[LeetCode](https://leetcode.cn/problems/summary-ranges/)**

![image-20230802215508636](https://img.enndfp.cn/image-20230802215508636.png)

![image-20230802215613085](https://img.enndfp.cn/image-20230802215613085.png)

**解题思路：**

慢指针指向每个区间的起始位置，快指针从慢指针位置开始向后遍历直到不满足连续递增（或快指针达到数组边界），则当前区间结束；然后将 slow指向更新为 fast + 1，作为下一个区间的开始位置，fast继续向后遍历找下一个区间的结束位置，如此循环，直到输入数组遍历完毕

```java
/**
     * 快慢指针
     *
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> res = new ArrayList<>();
        // slow 初始指向第 1 个区间的起始位置
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            // fast 向后遍历，直到不满足连续递增(即 nums[fast] + 1 != nums[fast + 1])
            // 或者 fast 达到数组边界，则当前连续递增区间 [slow, fast] 遍历完毕，将其写入结果列表
            if (fast + 1 == nums.length || nums[fast] + 1 != nums[fast + 1]) {
                // 将当前区间 [slow, fast] 写入结果列表
                StringBuilder sb = new StringBuilder();
                sb.append(nums[slow]);
                if (slow != fast) {
                    sb.append("->").append(nums[fast]);
                }
                res.add(sb.toString());
                // 将 slow 指向更新为 fast + 1，作为下一个区间的起始位置
                slow = fast + 1;
            }
        }
        return res;
    }
```

### 9.剑指 Offer 05. 替换空格

**题目地址：[剑指 Offer](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/description/)**

![image-20230802222536210](https://img.enndfp.cn/image-20230802222536210.png)

```java
/**
     * 字符串拼接
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
```

