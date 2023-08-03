package com.enndfp.charpter4_stack.level2;

import java.util.Stack;

/**
 * 最小栈
 *
 * @author Enndfp
 */
public class MinStack {
    // 当前栈的最小元素
    private long minElement;
    // 存储元素与当前最小值的差值
    private Stack<Long> stack;

    /**
     * 初始化堆栈对象
     */
    public MinStack() {
        stack = new Stack<>();
    }

    /**
     * 将元素val推入堆栈
     *
     * @param val
     */
    public void push(int val) {
        if (stack.isEmpty()) {
            // 第一个元素直接入栈，并将当前最小值设为val
            stack.push(0L);
            minElement = val;
        } else {
            // 将val与当前最小值的差值入栈
            stack.push(val - minElement);
            // 更新最小值
            if (val < minElement) {
                minElement = val;
            }
        }
    }

    /**
     * 删除堆栈顶部的元素
     */
    public void pop() {
        if (!stack.isEmpty()) {
            long top = stack.pop();
            // 如果top小于0，说明原来入栈的元素比当前最小值小，需要还原最小值
            if (top < 0) {
                minElement = minElement - top;
            }
        }
    }

    /**
     * 获取堆栈顶部的元素
     *
     * @return
     */
    public int top() {
        long top = stack.peek();
        // 如果top小于0，说明原来入栈的元素比当前最小值小，栈顶元素实际上为当前最小值
        if (top < 0) {
            return (int) minElement;
        } else {
            // 否则，栈顶元素为当前最小值加上差值，即实际元素值
            return (int) (minElement + top);
        }
    }

    /**
     * 获取堆栈中的最小元素
     *
     * @return
     */
    public int getMin() {
        return (int) minElement;
    }
}
