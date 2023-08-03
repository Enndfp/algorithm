package com.enndfp.charpter4_stack.level2;

import java.util.Stack;

/**
 * 有效的括号
 *
 * @author Enndfp
 */
public class IsValid {

    /**
     * 利用栈的先进后出
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        // 注意泛型要求包装类型
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            if (stack.isEmpty()) return false;
            if (c == ')' && stack.pop() != '(') return false;
            if (c == '}' && stack.pop() != '{') return false;
            if (c == ']' && stack.pop() != '[') return false;
        }
        // 返回的是判断栈是否为空
        return stack.isEmpty();
    }

}
