package cn.lcools.leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * Copyright: Copyright (c) 2020
 *
 * @ClassName: Solution20
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: liushuai
 * @date: 2020/8/14 8:42
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2020/8/14     liushuai           v1.0.0               修改原因
 */
public class Solution20 {
    public boolean isValid(String s) {
        int n = s.length();
        if ((n & 1) == 1) {
            return false;
        }
        String rule = "(){}[]";
        Stack<Character> stack = new Stack();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int index = rule.indexOf(c);
            if (index != -1) {
                Character top = stack.isEmpty() ? null : stack.peek();
                if (top != null && ((top == rule.charAt(0) && index == 1)
                        || (top == rule.charAt(2) && index == 3)
                        || (top == rule.charAt(4) && index == 5))) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
        Solution20 solution20 = new Solution20();
        String s = "[[](xx(11){22}[33])]()";
        boolean ret = solution20.isValid(s);
        System.out.println(ret);

    }
}
