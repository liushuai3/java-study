package cn.lcools.leetcode;

import org.junit.Test;

/**
 * Copyright: Copyright (c) 2020
 *
 * @ClassName: Solution43
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: liushuai
 * @date: 2020/8/13 17:06
 * *****
 * Modification History:
 * Date         Author          Version            Description
 * ---------------------------------------------------------*
 * 2020/8/13     liushuai           v1.0.0               修改原因
 */
public class Solution43 {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ret = "0";
        for(int i = num1.length()-1 ; i>=0 ; i-- ){
            StringBuilder curr = new StringBuilder();
            int x = num1.charAt(i) - '0';
            int pre = 0;
            for (int j = num1.length() - 1; j > i; j--) {
                curr.insert(0,0);
            }
            for (int j = num2.length()-1; j>=0 ; j--) {
                int y = num2.charAt(j) - '0';
                int rt = ((x*y) + pre) % 10;
                curr.insert(0,rt);
                pre = (x*y) / 10;
            }
            if(pre!=0){
                curr.insert(0,pre);
            }
            ret = addStrings(ret,curr.toString());
        }
        return ret;
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    @Test
    public void test(){
        Solution43 solution43 = new Solution43();
        String num1 = "9";
        String num2 = "99";
        String ret = solution43.multiply(num1,num2);
        System.out.println(num1 + "*" + num2 +" = "+ ret); //56088

    }
}
