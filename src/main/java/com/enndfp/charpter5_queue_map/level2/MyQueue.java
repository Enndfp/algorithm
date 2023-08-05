package com.enndfp.charpter5_queue_map.level2;

import java.util.Stack;

/**
 * 用栈实现队列
 *
 * @author Enndfp
 */
public class MyQueue {

    private Stack<Integer> A;
    private Stack<Integer> B;

    public MyQueue() {
        A = new Stack<>();
        B = new Stack<>();
    }

    public void push(int x) {
        A.push(x);
    }

    public int pop() {
        int peek = peek();
        B.pop();
        return peek;
    }

    public int peek() {
        if (!B.isEmpty()) return B.peek();
        if (A.isEmpty()) return -1;
        while (!A.isEmpty()) {
            B.push(A.pop());
        }
        return B.peek();
    }

    public boolean empty() {
        return A.isEmpty() && B.isEmpty();
    }
}
